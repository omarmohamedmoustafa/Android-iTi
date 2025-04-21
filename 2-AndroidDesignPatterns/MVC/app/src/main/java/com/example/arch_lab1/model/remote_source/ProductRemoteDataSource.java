package com.example.arch_lab1.model.remote_source;

import com.example.arch_lab1.model.pojo.ProductResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRemoteDataSource implements IProductRemoteDataSource{

    private NetworkCallback callback;
    private static String BASE_URL = "https://dummyjson.com/";
    private ProductApiService service;

    private static ProductRemoteDataSource instance = null;


    public static ProductRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new ProductRemoteDataSource();
        }
        return instance;
    }
    private ProductRemoteDataSource(){
        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductApiService.class);
    }
    public void makeNetworkCall(NetworkCallback callback) {
        Call<ProductResponse> call = service.getProducts();
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    callback.onSuccess(new ArrayList<>(response.body().getProducts()));
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

}
