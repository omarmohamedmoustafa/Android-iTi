package com.ities45.lab1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide

class ProductDetailsFragment : Fragment() {

    private var product: Product? = null
    lateinit var titleRes: TextView
    lateinit var priceRes: TextView
    lateinit var descRes: TextView
    lateinit var ratingBar: RatingBar
    lateinit var thumb: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getParcelable("PRODUCT")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleRes = view.findViewById<TextView>(R.id.titleRes)
        priceRes = view.findViewById<TextView>(R.id.priceRes)
        descRes = view.findViewById<TextView>(R.id.descRes)
        ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        thumb = view.findViewById<ImageView>(R.id.thumb)

        updateProduct(product)
    }


    companion object {
        fun newInstance(product: Product): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putParcelable("PRODUCT", product)
            fragment.arguments = args
            return fragment
        }
    }

    fun updateProduct(product: Product?) {
        product?.let {
            titleRes.text = it.title
            priceRes.text = "$${it.price}"
            descRes.text = it.description
            ratingBar.rating = it.rating ?: 0f // Safely handle null rating

            Glide.with(requireContext())
                .load(it.thumbnail)
                .into(thumb)
        }
    }
}
