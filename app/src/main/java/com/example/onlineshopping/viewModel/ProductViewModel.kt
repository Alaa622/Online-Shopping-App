package com.example.onlineshopping.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onlineshopping.data.local.ProductDatabase
import com.example.onlineshopping.model.ProductItem
import com.example.onlineshopping.repo.Repository
import kotlinx.coroutines.launch
import java.io.IOException

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository =
        Repository(ProductDatabase.getDatabase(application.applicationContext))

    private val _categories = MutableLiveData<List<String>>()
    val liveDataCategories: LiveData<List<String>> get() = _categories

    private val _productsOfCategory = MutableLiveData<List<ProductItem>>()
    val liveDataProductsOfCategory: LiveData<List<ProductItem>> get() = _productsOfCategory

    private val _allProducts = MutableLiveData<List<ProductItem>>()
    val liveDataAllProducts : LiveData<List<ProductItem>> get() = _allProducts

    init {
        getAllProductsFromRemote()
        getAllCategories()
        getAllProductsFromLocal()
    }

    private fun getAllProductsFromRemote() {
        viewModelScope.launch {
            safeFetchProductsFromRemote()
        }
    }

    private fun getAllProductsFromLocal(){
        viewModelScope.launch {
            _allProducts.value=repository.getAllLocalProducts()
        }
    }

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            _productsOfCategory.value = repository.getProductsByCategory(category)
        }

    }

    private fun getAllCategories() {
        viewModelScope.launch {
            _categories.value = repository.getAllCategories()
        }

    }

    private suspend fun safeFetchProductsFromRemote() {
        try {
            if (internetConnection(this.getApplication())) {
                val productResponse = repository.getAllRemoteProducts()
                if (productResponse.isSuccessful) {
                    productResponse.body()?.let { repository.insertAllProducts(it) }
                }
            }

        } catch (e: IOException) {
            throw IOException("No Internet Connection")
        }
    }

    private fun internetConnection(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            return getNetworkCapabilities(activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    }


}