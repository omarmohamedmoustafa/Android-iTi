package com.example.arch_lab2.model.repository;


import androidx.lifecycle.LiveData;

import com.example.arch_lab2.model.pojo.Product;
import com.example.arch_lab2.model.local_source.IProductLocalDataSource;
import com.example.arch_lab2.model.remote_source.IProductRemoteDataSource;
import com.example.arch_lab2.model.remote_source.NetworkCallback;
import java.util.List;

public class Repository implements IRepository {
    private final IProductLocalDataSource localDataSource;
    private final IProductRemoteDataSource remoteDataSource;

    private static IRepository instance = null;

    private Repository(IProductLocalDataSource localDataSource,
                       IProductRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static synchronized IRepository getInstance(IProductLocalDataSource localDataSource,
                                                       IProductRemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new Repository(localDataSource, remoteDataSource);
        }
        return instance;
    }

    @Override
    public LiveData<List<Product>> getStoredProducts() {
        return localDataSource.getAllProducts();
    }
    @Override
    public void getAllProductsFromRemoteSource(NetworkCallback callback){
        remoteDataSource.makeNetworkCall(callback);
    }

    @Override
    public void insertProduct(Product product) {
        localDataSource.insertProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        localDataSource.deleteProduct(product);
    }
}