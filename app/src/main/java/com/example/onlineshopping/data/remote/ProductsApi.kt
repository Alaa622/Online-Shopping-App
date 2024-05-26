package com.example.onlineshopping.data.remote

import com.example.onlineshopping.model.ProductItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("products/categories")
    suspend fun getAllCategories(): Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category:String):Response<List<ProductItem>>
}