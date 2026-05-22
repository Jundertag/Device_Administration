package com.jayden.deviceadministration.core.database.converters

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromStringList(list: List<String>): String = list.joinToString("|||")

    @TypeConverter
    fun toStringList(value: String): List<String> = value.split("|||")
}