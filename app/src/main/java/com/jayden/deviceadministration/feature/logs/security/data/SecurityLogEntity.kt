package com.jayden.deviceadministration.feature.logs.security.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SecurityLogEntity(
    @PrimaryKey val logTimestampNanos: Long,
    val logLevel: Int,
    val logTag: Int,
    val logId: Long,
    val logData: String?
)