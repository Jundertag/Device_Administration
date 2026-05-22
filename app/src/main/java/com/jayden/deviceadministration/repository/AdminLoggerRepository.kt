package com.jayden.deviceadministration.repository

import com.jayden.deviceadministration.app.model.DnsNetworkLog
import com.jayden.deviceadministration.app.model.SecurityLog
import com.jayden.deviceadministration.app.model.TcpNetworkLog
import com.jayden.deviceadministration.core.database.AppDatabase

class AdminLoggerRepository(
    val appDatabase: AppDatabase
) {
    suspend fun saveSecurityLogs(logs: List<SecurityLog>) = TODO("Save security logs")

    suspend fun saveTcpNetworkLogs(logs: List<TcpNetworkLog>) = TODO("Save TCP network logs")

    suspend fun saveDnsNetworkLogs(logs: List<DnsNetworkLog>) = TODO("Save DNS network logs")
}