package com.jayden.deviceadministration.data.room.network

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [
    TcpNetworkEventEntity::class,
    DnsNetworkEventEntity::class
], version = 1)
@TypeConverters(Converters::class)
abstract class NetworkLogDatabase : RoomDatabase() {
    abstract fun tcpNetworkEventDao(): TcpNetworkEventDao
    abstract fun dnsNetworkEventDao(): DnsNetworkEventDao
}

class Converters {
    @TypeConverter
    fun fromStringList(list: List<String>): String = list.joinToString("|||")

    @TypeConverter
    fun toStringList(value: String): List<String> = value.split("|||")
}