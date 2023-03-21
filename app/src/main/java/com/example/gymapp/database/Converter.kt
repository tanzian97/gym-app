package com.example.gymapp.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class Converter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromSetListToJSON(setList: SetList): String {
        return Gson().toJson(setList)
    }
    @TypeConverter
    fun fromJSONToSetList(json: String): SetList {
        return Gson().fromJson(json,SetList::class.java)
    }
}

