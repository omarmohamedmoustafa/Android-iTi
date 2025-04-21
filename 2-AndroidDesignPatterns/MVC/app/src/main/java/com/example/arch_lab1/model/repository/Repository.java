package com.example.arch_lab1.model.repository;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.arch_lab1.model.local_source.IProductLocalDataSource;
import com.example.arch_lab1.model.local_source.ProductDAO;
import com.example.arch_lab1.model.local_source.ProductDatabase;
import com.example.arch_lab1.model.pojo.Product;
import com.example.arch_lab1.model.remote_source.IProductRemoteDataSource;
import com.example.arch_lab1.model.remote_source.NetworkCallback;
import com.example.arch_lab1.model.remote_source.ProductRemoteDataSource;

import java.util.List;

public class Repository implements IRepository {
    private Context context;
    private ProductDAO productDao;
    private LiveData<List<Product>> all_products;
    private static Repository repository = null;


    private Repository(Context context) {
        this.context=context;
        ProductDatabase db =ProductDatabase.getInstance(context.getApplicationContext());
        productDao=db.ProductDAO();
        all_products=productDao.getAllProducts();
    }

    public static Repository getInstance(Context context)
    {
        if(repository ==null){
            repository=new Repository(context);
        }
        return repository;
    }

    @Override
    public LiveData<List<Product>> getStoredProducts() {
        return all_products;
    }

    @Override
    public void insertProduct(Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                productDao.insertProduct(product);
            }
        }).start();
    }

    @Override
    public void getAllProductsFromRemoteSource(NetworkCallback callback) {
        ProductRemoteDataSource remoteDataSource = ProductRemoteDataSource.getInstance();
        remoteDataSource.makeNetworkCall(callback);
    }

    @Override
    public void deleteProduct(Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                productDao.deleteProduct(product);
            }
        }).start();
    }
}