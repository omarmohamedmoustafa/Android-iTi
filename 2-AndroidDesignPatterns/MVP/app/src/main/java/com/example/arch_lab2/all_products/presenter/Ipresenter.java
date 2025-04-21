package com.example.arch_lab2.all_products.presenter;

import com.example.arch_lab2.model.pojo.Product;

public interface Ipresenter {
    void getAllProducts();

    void addToFavProducts(Product product);
}
