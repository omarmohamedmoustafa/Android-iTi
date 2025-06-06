package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.day5.R
import java.text.NumberFormat
import java.util.Locale

class ProductDetailsFragment : Fragment() {
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private var product: Product? = null
    private val TAG = "ProductDetailsFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getParcelable("PRODUCT")
        //  Log.d(TAG, "onCreate: Product received: ${product?.title}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProductDetails(product)

        binding.addToFav.setOnClickListener {
            if (isAdded) {
                // Favorite button logic
            }
        }
    }

    fun updateProductDetails(product: Product?) {
        if (view == null || !isAdded) return
        if (product == null) {
            Log.e(TAG, "updateProductDetails: Product is null")
            return
        }

        binding.titleRes.text = product.title
        binding.priceRes.text = NumberFormat.getCurrencyInstance(Locale.US).format(product.price)
        binding.descRes.text = product.description
        binding.ratingBar.rating = product.rating


        Glide.with(requireContext())
            .load(product.thumbnail)
            .into(binding.thumb)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(product: Product): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle().apply {
                putParcelable("PRODUCT", product)
            }
            fragment.arguments = args
            return fragment
        }
    }
}