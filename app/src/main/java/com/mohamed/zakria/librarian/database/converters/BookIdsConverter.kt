package com.mohamed.zakria.librarian.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.mohamed.zakria.librarian.App

class BookIdsConverter {

    @TypeConverter
    fun fromBookIds(list: List<String>): String = App.gson.toJson(list)


    @TypeConverter
    fun fromJson(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return try {
            App.gson.fromJson(json,type)
        } catch (t: Throwable) {
            emptyList()
        }

    }

}