package com.example.arch_lab2.model.remote_source;


import com.example.arch_lab2.model.pojo.Product;

import java.util.List;

public interface NetworkCallback {
    public void onSuccess(List<Product> Products);
    public void onFailure(String errorMessage);
}
