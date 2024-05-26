package com.example.onlineshopping.repo

import com.example.onlineshopping.data.remote.RetrofitInstance
import com.example.onlineshopping.model.ProductItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class Repository {

    suspend fun getAllCategories(): Response<List<String>>{
        return RetrofitInstance.productsApi.getAllCategories()
    }


    suspend fun getProductsByCategory(category:String): Response<List<ProductItem>>{
        return RetrofitInstance.productsApi.getProductsByCategory(category)
    }
}