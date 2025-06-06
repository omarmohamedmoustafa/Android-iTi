package com.example.mvvm.model.remote


import com.example.mvvm.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("products")
    suspend fun getProducts(): Response<ProductResponse>
}