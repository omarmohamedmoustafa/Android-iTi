package com.example.arch_lab2.fav_products.presenter;

import androidx.lifecycle.LiveData;

import com.example.arch_lab2.model.pojo.Product;

import java.util.List;

public interface IFavpresenter {
    LiveData<List<Product>> getLocalData();
    void remopveFromFav(Product product);
}
