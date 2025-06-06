package com.ities45.lab1

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val product = intent.getParcelableExtra<Product>("PRODUCT")
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE && product != null) {
            // In landscape mode, return the product to MainActivity and finish
            val resultIntent = Intent().apply {
                putExtra("PRODUCT", product)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
            return
        }

        // Proceed with portrait mode
        setContentView(R.layout.activity_second)
        if (product != null) {
            val fragment = ProductDetailsFragment.newInstance(product)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detailsContainer, fragment)
                .commit()
        }
    }

    override fun onBackPressed() {
        // Pass the product back to MainActivity in portrait mode
        val product = intent.getParcelableExtra<Product>("PRODUCT")
        if (product != null) {
            val resultIntent = Intent().apply {
                putExtra("PRODUCT", product)
            }
            setResult(RESULT_OK, resultIntent)
        }
        super.onBackPressed()
    }
}