package com.example.arch_lab2.all_products.view;

import com.example.arch_lab2.model.pojo.Product;

import java.util.List;

public interface IView {
    void showData(List<Product> products);
    void showError(String errorMessage);
}
