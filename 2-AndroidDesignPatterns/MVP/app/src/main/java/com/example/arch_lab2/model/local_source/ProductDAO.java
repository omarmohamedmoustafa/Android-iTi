package com.example.arch_lab2.model.local_source;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import com.example.arch_lab2.model.pojo.Product;


@Dao
public interface ProductDAO {

    @Query("SELECT * FROM products_table")
    LiveData<List<Product>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);


}