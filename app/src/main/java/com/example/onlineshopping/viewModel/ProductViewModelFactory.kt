package com.example.onlineshopping.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopping.repo.Repository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ProductViewModelFactory(private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(application) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel class")
    }
}