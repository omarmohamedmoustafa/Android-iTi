package com.example.arch_lab1.model.local_source;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.arch_lab1.model.pojo.Product;

import java.util.List;

public class ProductLocalDataSource implements IProductLocalDataSource {

    private final ProductDAO productDao;


    private LiveData<List<Product>> allProducts;

    private static ProductLocalDataSource source = null;


    private ProductLocalDataSource(Context context){
        ProductDatabase db = ProductDatabase.getInstance(context);
        productDao = db.ProductDAO();
        allProducts = productDao.getAllProducts();
    }
    public static ProductLocalDataSource getInstance(Context context){
        if (source == null){
            source = new ProductLocalDataSource(context);
        }
        return source;
    }

    @Override
    public void insertProduct(Product product){
        new Thread(new Runnable() {
            @Override
            public void run() {
                productDao.insertProduct(product);
            }
        }).start();
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

    @Override
    public LiveData<List<Product>> getAllProducts() {
        return productDao.getAllProducts();
    }

}
