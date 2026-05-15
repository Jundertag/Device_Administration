package com.jayden.deviceadministration.facade

import com.jayden.deviceadministration.app.model.SecurityLog
import com.jayden.deviceadministration.repository.AdminLoggerRepository

class AdminLoggerFacade(
    val repo: AdminLoggerRepository
) {
    fun saveSecurityLogs(
        logs: List<android.app.admin.SecurityLog.SecurityEvent>
    ) {
        repo.saveSecurityLogs(logs.map { log ->
            SecurityLog(
                logTimestampNanos = log.timeNanos,
                logLevel = log.logLevel,
                logTag = log.tag,
                logId = log.id,
                logData = log.data?.toString()
            )
        })
    }
}