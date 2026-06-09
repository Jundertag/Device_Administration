package com.jayden.deviceadministration.feature.logs.tcp.domain

data class TcpNetworkEvent(
    val timestamp: Long,
    val packageName: String,
    val ipAddress: String,
    val port: Int
)