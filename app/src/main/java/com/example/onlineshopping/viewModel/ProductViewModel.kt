package com.example.onlineshopping.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopping.model.ProductItem
import com.example.onlineshopping.repo.Repository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<String>>()
    val liveDataCategories: LiveData<List<String>> get() = _categories

    private val _products = MutableLiveData<List<ProductItem>>(emptyList())
    val liveDataProducts: LiveData<List<ProductItem>> get() = _products

    private val repository = Repository()


    init {
        getAllCategories()
    }


    private fun getAllCategories() {
        viewModelScope.launch {
            val response = repository.getAllCategories()
            if (response.isSuccessful) {
                _categories.postValue(response.body())
            }

        }

    }

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            val response = repository.getProductsByCategory(category)
            if (response.isSuccessful) {
                _products.postValue(response.body())
            }
        }
    }
}