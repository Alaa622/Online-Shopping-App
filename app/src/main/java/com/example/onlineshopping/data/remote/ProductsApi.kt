package com.example.onlineshopping.data.remote

import com.example.onlineshopping.model.ProductItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductItem>>
}