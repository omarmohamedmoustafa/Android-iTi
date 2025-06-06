package com.ities45.lab1

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var dynamicFragment: ProductFragment
    private lateinit var fragmentMngr: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    private var dynamicFragment2: ProductDetailsFragment? = null

    // Activity Result Launcher for SecondActivity
    private val productResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val product = data?.getParcelableExtra<Product>("PRODUCT")
            if (product != null) {
                // Clear existing fragment in detailsContainer to avoid conflicts
                supportFragmentManager.findFragmentById(R.id.detailsContainer)?.let { fragment ->
                    supportFragmentManager.beginTransaction().remove(fragment).commitNow()
                }
                dynamicFragment2 = ProductDetailsFragment.newInstance(product)
                val containerId = if (resources.configuration.orientation == ORIENTATION_PORTRAIT) {
                    R.id.listContainer
                } else {
                    R.id.detailsContainer
                }
                supportFragmentManager.beginTransaction()
                    .replace(containerId, dynamicFragment2!!, "fragment2")
                    .addToBackStack(null) // Allow back to product list
                    .commit()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dynamicFragment = ProductFragment()
        fragmentMngr = supportFragmentManager
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.listContainer, dynamicFragment, "activity1")
        fragmentTransaction.commit()
    }

    override fun showProductDetails(product: Product) {
        if (resources.configuration.orientation == ORIENTATION_PORTRAIT) {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("PRODUCT", product)
            productResultLauncher.launch(intent)
        } else {
            // In landscape mode, show directly in detailsContainer
            // Clear existing fragment to avoid conflicts
            supportFragmentManager.findFragmentById(R.id.detailsContainer)?.let { fragment ->
                supportFragmentManager.beginTransaction().remove(fragment).commitNow()
            }
            dynamicFragment2 = ProductDetailsFragment.newInstance(product)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detailsContainer, dynamicFragment2!!, "fragment2")
                .addToBackStack(null) // Allow back to product list
                .commit()
        }
    }
}