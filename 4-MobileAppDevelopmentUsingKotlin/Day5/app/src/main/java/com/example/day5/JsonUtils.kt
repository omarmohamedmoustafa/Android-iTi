package com.example.myapplication

import android.content.Context
import android.util.Log
import com.google.gson.Gson

object JsonUtils {
    fun readProducts(context: Context): List<Product> {
        return try {
            val jsonString = context.assets.open("products.json")
                .bufferedReader()
                .use { it.readText() }
            val response = Gson().fromJson(jsonString, ProductResponse::class.java)
            response?.products ?: emptyList()
        } catch (e: Exception) {
            Log.e("JsonUtils", "Error reading products", e)
            emptyList()
        }
    }
}
