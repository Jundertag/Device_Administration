package com.jayden.deviceadministration.repository

import com.jayden.deviceadministration.app.model.SecurityLog
import com.jayden.deviceadministration.data.room.network.NetworkLogDatabase
import com.jayden.deviceadministration.data.room.security.SecurityLogDatabase

class AdminLoggerRepository(
    val networkLogDatabase: NetworkLogDatabase,
    val securityLogDatabase: SecurityLogDatabase
) {
    fun storeSecurityLogs(logs: List<android.app.admin.SecurityLog.SecurityEvent>) {
        val modeledLogs = logs.map { securityEvent ->
            SecurityLog(
                logTimestampNanos = securityEvent.timeNanos,
                logLevel = securityEvent.logLevel,
                logTag = securityEvent.tag,
                logId = securityEvent.id,
                logData = securityEvent.data?.toString()
            )
        }


    }

    fun storeNetworkLogs(logs: List<android.app.admin.NetworkEvent>) {

    }
}