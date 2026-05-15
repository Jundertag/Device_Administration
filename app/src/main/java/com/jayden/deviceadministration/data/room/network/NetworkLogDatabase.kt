package com.jayden.deviceadministration.data.room.network

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    TcpNetworkEventEntity::class,
    DnsNetworkEventEntity::class
], version = 1)
abstract class NetworkLogDatabase : RoomDatabase() {
    abstract fun tcpNetworkEventDao(): TcpNetworkEventDao
    abstract fun dnsNetworkEventDao(): DnsNetworkEventDao
}