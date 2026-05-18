package com.jayden.deviceadministration.repository.logger

import android.app.admin.ConnectEvent
import android.app.admin.DnsEvent
import android.app.admin.NetworkEvent
import android.app.admin.SecurityLog
import com.jayden.deviceadministration.app.model.DnsNetworkLog
import com.jayden.deviceadministration.app.model.TcpNetworkLog

class AdminLoggerFacade {
    fun mapSecurityLogs(
        logs: List<SecurityLog.SecurityEvent>
    ): List<com.jayden.deviceadministration.app.model.SecurityLog> = logs.map { log ->
        com.jayden.deviceadministration.app.model.SecurityLog(
            logTimestampNanos = log.timeNanos,
            logLevel = log.logLevel,
            logTag = log.tag,
            logId = log.id,
            logData = log.data?.toString()
        )
    }

    fun filterTcpNetworkLogs(
        logs: List<NetworkEvent>
    ): List<TcpNetworkLog> = logs.filterIsInstance<ConnectEvent>().map { log ->
        TcpNetworkLog(
            logTimestamp = log.timestamp,
            logId = log.id,
            logPackage = log.packageName,
            ip = log.inetAddress.hostAddress!!,
            port = log.port
        )
    }

    fun filterDnsNetworkLogs(
        logs: List<NetworkEvent>
    ): List<DnsNetworkLog> = logs.filterIsInstance<DnsEvent>().map { log ->
        DnsNetworkLog(
            logTimestamp = log.timestamp,
            logId = log.id,
            logPackage = log.packageName,
            hostname = log.hostname,
            returnedIpAddresses = log.inetAddresses.map { it.hostAddress!! },
            resolvedAddresses = log.totalResolvedAddressCount
        )
    }
}