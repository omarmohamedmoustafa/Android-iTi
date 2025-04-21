package com.example.arch_lab2.all_products.presenter;

import com.example.arch_lab2.all_products.view.IView;
import com.example.arch_lab2.model.pojo.Product;
import com.example.arch_lab2.model.remote_source.NetworkCallback;
import com.example.arch_lab2.model.repository.IRepository;

import java.util.List;

public class Presenter implements Ipresenter, NetworkCallback {
    IRepository repository;
    IView view;
    public Presenter(IView view, IRepository repo) {
        this.view = view;
        this.repository = repo;
    }
    @Override
    public void getAllProducts() {
        repository.getAllProductsFromRemoteSource(this);
    }
    @Override
    public void addToFavProducts(Product product) {
        repository.insertProduct(product);
    }

    @Override
    public void onSuccess(List<Product> Products) {
        view.showData(Products);
    }

    @Override
    public void onFailure(String errorMessage) {
        view.showError(errorMessage);
    }
}
