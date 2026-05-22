package com.jayden.deviceadministration.feature.logs.dns.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DnsNetworkEventEntity(
    @PrimaryKey val logTimestamp: Long,
    val logId: Long,
    val logPackage: String,
    val logHostname: String,
    val logIpAddresses: List<String>,
    val logAddressesResolved: Int
)