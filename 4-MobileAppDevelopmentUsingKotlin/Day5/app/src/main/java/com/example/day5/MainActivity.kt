package com.example.day5

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.myapplication.Communicator
import com.google.android.gms.analytics.ecommerce.Product

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dynamicFragment: ProductFragment
    private var dynamicFragment2: ProductDetailsFragment? = null
    private var selectedProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedProduct = savedInstanceState?.getParcelable("SELECTED_PRODUCT")
            ?: intent?.getParcelableExtra("PRODUCT")

        dynamicFragment = ProductFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.listContainer, dynamicFragment, "activity1")
            .commit()

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            selectedProduct?.let { showProductDetails(it) }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("SELECTED_PRODUCT", selectedProduct)
    }

    override fun showProductDetails(product: Product) {
        selectedProduct = product

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("PRODUCT", product)
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(intent)
        } else {
            updateDetailsFragment(product)
        }
    }

    private fun updateDetailsFragment(product: Product) {
        supportFragmentManager.executePendingTransactions()

        dynamicFragment2?.let { fragment ->
            fragment.updateProductDetails(product)
        } ?: run {
            dynamicFragment2 = ProductDetailsFragment.newInstance(product)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detailsContainer, dynamicFragment2!!)
                .commit()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.getParcelableExtra<Product>("PRODUCT")?.let { product ->
            selectedProduct = product
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                updateDetailsFragment(product)
            }
        }
    }
}