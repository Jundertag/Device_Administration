package com.jayden.deviceadministration.repository.logger

import com.jayden.deviceadministration.app.model.DnsNetworkLog
import com.jayden.deviceadministration.app.model.SecurityLog
import com.jayden.deviceadministration.app.model.TcpNetworkLog
import com.jayden.deviceadministration.data.room.network.DnsNetworkEventEntity
import com.jayden.deviceadministration.data.room.network.NetworkLogDatabase
import com.jayden.deviceadministration.data.room.network.TcpNetworkEventEntity
import com.jayden.deviceadministration.data.room.security.SecurityLogDatabase
import com.jayden.deviceadministration.data.room.security.SecurityLogEntity

class AdminLoggerRepository(
    val networkLogDatabase: NetworkLogDatabase,
    val securityLogDatabase: SecurityLogDatabase,

) {
    suspend fun saveSecurityLogs(logs: List<SecurityLog>) {
        securityLogDatabase.securityLogDao().insertAll(logs.map { log ->
            SecurityLogEntity(
                logTimestampNanos = log.logTimestampNanos,
                logLevel = log.logLevel,
                logTag = log.logTag,
                logId = log.logId,
                logData = log.logData
            )
        })
    }

    suspend fun saveTcpNetworkLogs(logs: List<TcpNetworkLog>) {
        networkLogDatabase.tcpNetworkEventDao().insertAll(logs.map { log ->
            TcpNetworkEventEntity(
                logTimestamp = log.logTimestamp,
                logId = log.logId,
                logPackageName = log.logPackage,
                logIpAddress = log.ip,
                logPort = log.port
            )
        })
    }

    suspend fun saveDnsNetworkLogs(logs: List<DnsNetworkLog>) {
        networkLogDatabase.dnsNetworkEventDao().insertAll(logs.map { log ->
            DnsNetworkEventEntity(
                logTimestamp = log.logTimestamp,
                logId = log.logId,
                logPackage = log.logPackage,
                logHostname = log.hostname,
                logIpAddresses = log.returnedIpAddresses,
                logAddressesResolved = log.resolvedAddresses,
            )
        })
    }
}