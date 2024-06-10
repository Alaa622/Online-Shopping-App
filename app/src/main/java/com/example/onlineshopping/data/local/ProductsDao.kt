package com.example.onlineshopping.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineshopping.model.ProductItem

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(products: List<ProductItem>)

    @Query("select * from productItem order by random()")
    suspend fun getAllProducts():List<ProductItem>

    @Query("select * from ProductItem where category= :category")
    suspend fun getProductsByCategory(category: String): List<ProductItem>

    @Query("select distinct category from ProductItem")
    suspend fun getAllCategories(): List<String>

}