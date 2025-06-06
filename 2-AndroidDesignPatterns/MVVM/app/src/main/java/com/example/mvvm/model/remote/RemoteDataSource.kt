package com.example.mvvm.model.remote

import com.example.mvvm.model.Product
import com.example.mvvm.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteDataSource {

    suspend fun getProducts(): Result<List<Product>> = withContext(Dispatchers.IO) {
        val response: Response<ProductResponse> = RetrofitClient.myApiService.getProducts()
        if (response.isSuccessful) {
            response.body()?.products?.let { products ->
                Result.success(products)
            } ?: Result.failure(Exception("Empty response"))
        } else {
            Result.failure(Exception("API error: ${response.code()}"))
        }
    }
}