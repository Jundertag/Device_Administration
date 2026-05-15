package com.jayden.deviceadministration.data.room.network

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DnsNetworkEventEntity(
    @PrimaryKey(autoGenerate = true) val logId: Long,
    val logHostname: String,
    val logAddresses: List<String>,
    val logAddressesResolved: Int
)