package com.example.mvvm.all_products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.Product
import com.example.mvvm.model.repository.ProductRepository
import kotlinx.coroutines.launch

class AllProductsViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            val result = repository.getProducts()
            result.onSuccess { products ->
                _products.postValue(products)
            }.onFailure {
                _products.postValue(emptyList())
            }
        }
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }
}