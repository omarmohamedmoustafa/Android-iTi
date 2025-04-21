package com.example.arch_lab2.model.repository;

import androidx.lifecycle.LiveData;

import com.example.arch_lab2.model.pojo.Product;
import com.example.arch_lab2.model.remote_source.NetworkCallback;

import java.util.List;

public interface IRepository {
    LiveData<List<Product>> getStoredProducts();

    void insertProduct(Product product);

    void getAllProductsFromRemoteSource(NetworkCallback callback);

    void deleteProduct(Product product);
}
