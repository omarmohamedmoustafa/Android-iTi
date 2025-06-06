package com.ities45.lab1

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

class ProductsListAdapter(private val onItemClick: (Product) -> Unit) : ListAdapter<Product, ProductsListAdapter.ProductViewHolder>(ProductDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.prod_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val product = getItem(position)
        holder.prodName.text = product.title
        Glide.with(holder.prodThumb.context).load(product.thumbnail).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.prodThumb)
        holder.view.setOnClickListener { onItemClick(product) }
    }

    class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val prodThumb : ImageView = view.findViewById(R.id.prodImg)
        val prodName : TextView = view.findViewById(R.id.prodName)
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }

    }
}