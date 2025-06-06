package com.ities45.lab1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val rating: Float,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
) : Parcelable

// Root response structure
data class ProductResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)