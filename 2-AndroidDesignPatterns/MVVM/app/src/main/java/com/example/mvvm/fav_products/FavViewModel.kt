package com.example.mvvm.fav_products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.Product
import com.example.mvvm.model.repository.ProductRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavViewModel(private val repository: ProductRepository)  {
    private val _favoriteProducts = MutableLiveData<List<Product>>()
    val favoriteProducts: LiveData<List<Product>> get() = _favoriteProducts

    init {
        fetchFavoriteProducts()
    }

    fun fetchFavoriteProducts() {
        GlobalScope.launch {
            var products = repository.getFavoriteProducts()
            _favoriteProducts.postValue(products)
        }
    }
    fun deleteProduct(product: Product) {
        GlobalScope.launch {
            repository.deleteProduct(product)
            var products = repository.getFavoriteProducts()
            _favoriteProducts.postValue(products)
        }
    }
}