package com.example.arch_lab1.model.remote_source;

import com.example.arch_lab1.model.pojo.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApiService {
    @GET("products")
    Call<ProductResponse> getProducts();
}
