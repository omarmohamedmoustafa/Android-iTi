package com.example.mvvm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvvm.all_products.AllProductsActivity
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.fav_products.FavProductsActivity
import com.example.mvvm.model.local.LocalDataSource
import com.example.mvvm.model.remote.RemoteDataSource
import com.example.mvvm.model.repository.ProductRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAllProducts.setOnClickListener {
            startActivity(Intent(this, AllProductsActivity::class.java))
        }

        binding.btnFavProducts.setOnClickListener {
            startActivity(Intent(this, FavProductsActivity::class.java))
        }

        binding.btnExit.setOnClickListener {
            finish()
        }

    }
}