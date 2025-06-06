package com.example.mvvm.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val rating: Float,
    val category: String,
    val thumbnail: String
) : Parcelable

data class ProductResponse(
    val products: List<Product>
)