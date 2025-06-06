package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.day5.R
import com.example.day5.databinding.FragmentProductBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var myAdapter: ProductsListAdapter
    private lateinit var communicator: Communicator
    private var selectedPosition = RecyclerView.NO_POSITION
    private lateinit var productDao: ProductDao

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = context as Communicator
        try {
            productDao = AppDatabase.getDatabase(context).productDao()
        } catch (e: Exception) {
            Log.e("ProductFragment", "Failed to initialize Room database", e)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = ProductsListAdapter { product ->
            communicator.showProductDetails(product)
        }
        binding.rvProd.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }

        if (savedInstanceState != null) {
            selectedPosition = savedInstanceState.getInt("SELECTED_POSITION", RecyclerView.NO_POSITION)
        }

        loadProducts()
    }

    private fun loadProducts() {
        lifecycleScope.launch{
            var products: List<Product> = emptyList()
            var dataSourceMessage = ""

            if (NetworkUtils.isNetworkConnected(requireContext())) {
                try {
                    // Fetch from API
                    Log.d("ProductFragment", "Attempting API fetch from https://dummyjson.com/products")
                    val response = withContext(Dispatchers.IO) {
                        RetrofitClient.apiService.getProducts()
                    }

                    if (response.isSuccessful) {
                        products = response.body()?.products ?: emptyList()
                        dataSourceMessage = "Products loaded from API (${products.size} items)"

                        if (products.isNotEmpty()) {
                            withContext(Dispatchers.IO) {
                                productDao.clearProducts()

//                                productDao.insertProducts(products.map { it.toProductEntity() })
                                productDao.insertProducts(products.map { it.toProductEntity() })
                                Log.d("ProductFragment", "Stored ${products.size} products in Room")
                            }
                        }
                    } else {
                        Log.e("ProductFragment", "API failed: HTTP ${response.code()}")
                        products = getCachedProducts()
                        dataSourceMessage = "API failed, showing cached data"
                    }
                } catch (e: Exception) {
                    Log.e("ProductFragment", "Error during API fetch${e.message}", e)
                    products = getCachedProducts()
                    dataSourceMessage = "Error occurred, showing cached data"
                }
            } else {
                Log.d("ProductFragment", "No network connection, loading from Room")
                products = getCachedProducts()
                dataSourceMessage = "No network, showing cached data"
            }

            // Update UI
            myAdapter.submitList(products)
            if (isAdded) {
                Toast.makeText(
                    context,
                    if (products.isEmpty()) "No data available" else dataSourceMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
            Log.d("ProductFragment", "Loaded ${products.size} products: $dataSourceMessage")
        }
    }


    private suspend fun getCachedProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val cachedProducts = productDao.getAllProducts().map { it.toProduct() }
                Log.d("ProductFragment", "Retrieved ${cachedProducts.size} products from Room")
                cachedProducts
            } catch (e: Exception) {
                Log.e("ProductFragment", "Error reading from Room", e)
                emptyList()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SELECTED_POSITION", selectedPosition)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}