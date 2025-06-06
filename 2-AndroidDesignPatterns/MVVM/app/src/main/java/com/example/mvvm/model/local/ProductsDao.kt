package com.example.mvvm.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.model.Product

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(product : Product)


    @Query("SELECT * FROM products")
    suspend fun getFavoriteProducts(): List<Product>

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProduct(productId: Int)
}