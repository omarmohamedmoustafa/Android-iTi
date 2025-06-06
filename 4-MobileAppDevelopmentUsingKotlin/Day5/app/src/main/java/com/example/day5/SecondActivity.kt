package com.example.myapplication


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.day5.MainActivity
import com.example.day5.R
import com.example.day5.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var currentProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentProduct = intent.getParcelableExtra("PRODUCT")
        if (currentProduct != null) {
            showProductFragment(currentProduct!!)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            currentProduct?.let { product ->
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("PRODUCT", product)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showProductFragment(product: Product) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.detailsContainer, ProductDetailsFragment.newInstance(product))
            .commit()
    }
}