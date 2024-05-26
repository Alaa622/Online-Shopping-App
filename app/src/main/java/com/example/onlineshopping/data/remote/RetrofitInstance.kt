package com.example.onlineshopping.data.remote

import com.example.onlineshopping.data.remote.Constants.Base_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(Base_URL).
        addConverterFactory(GsonConverterFactory.create()).build()
    }

    val productsApi: ProductsApi by lazy {
        retrofit.create(ProductsApi::class.java)
    }
}