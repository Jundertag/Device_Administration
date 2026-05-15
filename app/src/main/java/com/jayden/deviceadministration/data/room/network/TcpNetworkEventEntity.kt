package com.jayden.deviceadministration.data.room.network

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TcpNetworkEventEntity(
    @PrimaryKey val logTimestamp: Long,
    val logId: Long,
    val logPackageName: String,
    val logIpAddress: String,
    val logPort: Int,
)