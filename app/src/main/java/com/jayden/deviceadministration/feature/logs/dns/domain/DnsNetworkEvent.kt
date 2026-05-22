package com.jayden.deviceadministration.feature.logs.dns.domain

data class DnsNetworkEvent(
    val dnsIpAddress: String,
    val dnsPort: Int,
    val `package`: String,
    val hostname: String,
    val ipAddressesReturned: List<String>,
    val ipAddressesResolved: Int,
)