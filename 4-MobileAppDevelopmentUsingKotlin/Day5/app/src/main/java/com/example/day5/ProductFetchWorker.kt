package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ProductFetchWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    companion object {
        const val KEY_RESULT = "KEY_RESULT"
        const val KEY_ERROR = "KEY_ERROR"
        const val PREFS_NAME = "ProductPrefs"
        const val KEY_PRODUCTS_JSON = "products_json"
        private const val TAG = "ProductFetchWorker"
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Starting product fetch")
            val response = RetrofitClient.apiService.getProducts()
            if (response.isSuccessful) {
                val productResponse = response.body()
                if (productResponse != null && productResponse.products.isNotEmpty()) {
                    val productsJson = Gson().toJson(productResponse.products)
                    val prefs = applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                    prefs.edit().putString(KEY_PRODUCTS_JSON, productsJson).apply()
                    Log.d(TAG, "Fetched and saved ${productResponse.products.size} products")
                    Result.success(workDataOf(KEY_RESULT to "success"))
                } else {
                    Log.e(TAG, "Empty or null response body")
                    Result.failure(workDataOf(KEY_ERROR to "No products found in response"))
                }
            } else {
                Log.e(TAG, "API call failed with code: ${response.code()}")
                Result.failure(workDataOf(KEY_ERROR to "API call failed: ${response.code()} - ${response.message()}"))
            }
        } catch (e: IOException) {
            Log.e(TAG, "Network error: ${e.message}", e)
            Result.retry()
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error: ${e.message}", e)
            Result.failure(workDataOf(KEY_ERROR to "Error: ${e.message}"))
        }
    }
}