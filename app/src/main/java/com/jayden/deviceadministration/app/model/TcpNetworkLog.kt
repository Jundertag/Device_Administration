package com.jayden.deviceadministration.app.model

data class TcpNetworkLog(
    val logTimestamp: Long,
    val logId: Long,
    val logPackage: String,
    val ip: String,
    val port: Int,
)