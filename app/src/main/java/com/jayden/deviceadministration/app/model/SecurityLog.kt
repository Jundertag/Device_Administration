package com.jayden.deviceadministration.app.model

data class SecurityLog(
    val logTimestampNanos: Long,
    val logLevel: Int,
    val logTag: Int,
    val logId: Long,
    val logData: String?
)