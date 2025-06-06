package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.day5.R
import com.example.day5.databinding.ProdItemBinding
import java.text.NumberFormat
import java.util.Locale

class ProductsListAdapter(private val onItemClick: (Product) -> Unit) :
    ListAdapter<Product, ProductsListAdapter.ProductViewHolder>(ProductDiffUtil()) {

    private val TAG = "ProductsListAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        try {
            holder.bind(product)
            holder.itemView.setOnClickListener { onItemClick(product) }
        } catch (e: Exception) {
            Log.e(TAG, "Error binding product view", e)
        }
    }

    class ProductViewHolder(private val binding: ProdItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.prodName.text = product.title
            binding.prodPrice.text = NumberFormat.getCurrencyInstance(Locale.US).format(product.price)

            Glide.with(binding.prodImg.context)
                .load(product.thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.prodImg)
        }
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}