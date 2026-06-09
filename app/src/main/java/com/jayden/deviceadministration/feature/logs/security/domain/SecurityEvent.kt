package com.jayden.deviceadministration.feature.logs.security.domain

data class SecurityEvent(
    val timestamp: Long,
    val id: Long,
    val logLevel: Int,
    val tag: Int,
    val data: String?,
)