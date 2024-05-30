package com.example.onlineshopping.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.onlineshopping.model.ProductItem

@Database(entities = [ProductItem::class], version = 1, exportSchema = false)
@TypeConverters(RatingConverter::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductsDao(): ProductsDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null
        fun getDatabase(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "Product Database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}