package com.example.onlineshopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductItem(
    val category: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
