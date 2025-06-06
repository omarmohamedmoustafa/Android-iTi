package com.example.mvvm.all_products

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityAllProductsBinding
import com.example.mvvm.model.Product
import com.example.mvvm.model.local.AppDatabase
import com.example.mvvm.model.local.LocalDataSource
import com.example.mvvm.model.remote.RemoteDataSource
import com.example.mvvm.model.repository.ProductRepository

class AllProductsActivity : AppCompatActivity(), OnProductClicked {
    private lateinit var binding: ActivityAllProductsBinding
    private lateinit var viewModel: AllProductsViewModel
    private lateinit var adapter: ProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAllProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val repository = ProductRepository.getInstance(RemoteDataSource(), LocalDataSource(AppDatabase.getDatabase(this).productDao()))
        viewModel = AllProductsViewModel(repository)

        adapter = ProductsAdapter(this, onFavoriteClick = { product ->
            viewModel.insertProduct(product)
        })
        binding.recyclerViewAllProducts.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewAllProducts.adapter = adapter

        viewModel.products.observe(this) { products ->
            if (products.isEmpty()) {
                Toast.makeText(this, "No products available", Toast.LENGTH_SHORT).show()
            } else {
                adapter.submitList(products)
            }
        }
    }

    override fun onProductClicked(product: Product) {
        // toast to inform the user that the product added to  favorites
        Toast.makeText(this, "Added To Favourites", Toast.LENGTH_SHORT).show()
            viewModel.insertProduct(product)
    }
}