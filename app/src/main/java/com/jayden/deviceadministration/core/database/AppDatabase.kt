package com.jayden.deviceadministration.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jayden.deviceadministration.core.database.converters.Converters
import com.jayden.deviceadministration.feature.logs.dns.data.DnsNetworkEventDao
import com.jayden.deviceadministration.feature.logs.dns.data.DnsNetworkEventEntity
import com.jayden.deviceadministration.feature.logs.tcp.data.TcpNetworkEventDao
import com.jayden.deviceadministration.feature.logs.tcp.data.TcpNetworkEventEntity
import com.jayden.deviceadministration.feature.logs.security.data.SecurityLogDao
import com.jayden.deviceadministration.feature.logs.security.data.SecurityLogEntity

@Database(
    entities = [
        TcpNetworkEventEntity::class,
        DnsNetworkEventEntity::class,
        SecurityLogEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tcpNetworkEventDao(): TcpNetworkEventDao
    abstract fun dnsNetworkEventDao(): DnsNetworkEventDao
    abstract fun securityLogDao(): SecurityLogDao
}