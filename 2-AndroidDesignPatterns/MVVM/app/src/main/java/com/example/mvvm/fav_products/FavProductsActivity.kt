package com.example.mvvm.fav_products

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.databinding.ActivityFavProductsBinding
import com.example.mvvm.model.Product
import com.example.mvvm.model.local.AppDatabase
import com.example.mvvm.model.local.LocalDataSource
import com.example.mvvm.model.remote.RemoteDataSource
import com.example.mvvm.model.repository.ProductRepository

class FavProductsActivity : AppCompatActivity(), com.example.mvvm.fav_products.OnProductClicked {
    private lateinit var binding: ActivityFavProductsBinding
    private lateinit var viewModel: FavViewModel
    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val database = AppDatabase.getDatabase(this)
        val productDao = database.productDao()
        val repository = ProductRepository.getInstance(RemoteDataSource(), LocalDataSource(productDao))

        viewModel = FavViewModel(repository)
        adapter = ProductsAdapter(this)
        binding.recyclerViewFavProducts.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFavProducts.adapter = adapter

        viewModel.favoriteProducts.observe(this) { products ->
            if (products.isEmpty()) {
                Toast.makeText(this, "No favorite products", Toast.LENGTH_SHORT).show()
            }
            adapter.submitList(products)
        }
    }

    override fun onProductClicked(product: Product) {
        viewModel.deleteProduct(product)
    }
}