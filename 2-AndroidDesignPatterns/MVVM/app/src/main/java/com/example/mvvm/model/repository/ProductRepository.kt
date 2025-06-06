package com.example.mvvm.model.repository

import android.util.Log
import com.example.mvvm.model.Product
import com.example.mvvm.model.local.LocalDataSource
import com.example.mvvm.model.remote.RemoteDataSource

class ProductRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    companion object {
        @Volatile
        private var instance: ProductRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): ProductRepository {
            return instance ?: synchronized(this) {
                instance ?: ProductRepository(remoteDataSource, localDataSource).also {
                    instance = it
                }
            }
        }
    }
    suspend fun getProducts(): Result<List<Product>> {
        return try {
            val remoteResult = remoteDataSource.getProducts()
            if (remoteResult.isSuccess) {
                Result.success(remoteResult.getOrNull() ?: emptyList())
            } else {
                Result.failure(remoteResult.exceptionOrNull() ?: Exception("Failed to fetch products"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFavoriteProducts(): List<Product> {
        return localDataSource.getFavoriteProducts()
    }
    suspend fun insertProduct(product: Product) {
        localDataSource.insertProducts(product)
    }
    suspend fun deleteProduct(product: Product) {
        localDataSource.deleteProduct(product)
    }

}