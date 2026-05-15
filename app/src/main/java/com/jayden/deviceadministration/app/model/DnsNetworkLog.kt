package com.jayden.deviceadministration.app.model

data class DnsNetworkLog(
    val logTimestamp: Long,
    val logId: Long,
    val logPackage: String,
    val hostname: String,
    val returnedIpAddresses: List<String>,
    val resolvedAddresses: Int,
)