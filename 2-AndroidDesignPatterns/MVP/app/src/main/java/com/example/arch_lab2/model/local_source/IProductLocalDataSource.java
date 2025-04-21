package com.example.arch_lab2.model.local_source;

import androidx.lifecycle.LiveData;
import com.example.arch_lab2.model.pojo.Product;
import java.util.List;

public interface IProductLocalDataSource {
    void insertProduct(Product product);
    void deleteProduct(Product product);
    LiveData<List<Product>> getAllProducts();
}