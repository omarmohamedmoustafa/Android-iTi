package com.example.mvvm.model.local

import com.example.mvvm.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val productDao: ProductsDao) {

    suspend fun insertProducts(product: Product) = withContext(Dispatchers.IO) {
        productDao.insertProducts(product)
    }

    suspend fun getFavoriteProducts(): List<Product> = withContext(Dispatchers.IO) {
        productDao.getFavoriteProducts()
    }
    suspend fun deleteProduct(product: Product) = withContext(Dispatchers.IO) {
        productDao.deleteProduct(product.id)
    }
}