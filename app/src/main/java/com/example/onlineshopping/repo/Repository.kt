package com.example.onlineshopping.repo

import com.example.onlineshopping.data.local.ProductDatabase
import com.example.onlineshopping.data.remote.RetrofitInstance
import com.example.onlineshopping.model.ProductItem
import retrofit2.Response

class Repository(private val productDatabase: ProductDatabase) {

    suspend fun getAllRemoteProducts():Response<List<ProductItem>>{
        return RetrofitInstance.productsApi.getAllProducts()
    }

    suspend fun getAllLocalProducts():List<ProductItem>{
        return productDatabase.getProductsDao().getAllProducts()
    }

    suspend fun insertAllProducts(products:List<ProductItem>){
        productDatabase.getProductsDao().insertAllProducts(products)
    }


    suspend fun getProductsByCategory(category:String):List<ProductItem>{
        return productDatabase.getProductsDao().getProductsByCategory(category)
    }

    suspend fun getAllCategories():List<String>{
        return productDatabase.getProductsDao().getAllCategories()
    }
}
