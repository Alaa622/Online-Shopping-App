package com.example.onlineshopping.data.local

import androidx.room.TypeConverter
import com.example.onlineshopping.model.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RatingConverter {

    @TypeConverter
    fun fromRating(rating: Rating): String{
        return Gson().toJson(rating)
    }

    @TypeConverter
    fun toRating(rating:String):Rating {
        val type=object : TypeToken<Rating>(){}.type
        return Gson().fromJson(rating,type)
    }
}