package com.jayden.deviceadministration.facade

import android.app.admin.ConnectEvent
import android.app.admin.DnsEvent
import com.jayden.deviceadministration.app.model.DnsNetworkLog
import com.jayden.deviceadministration.app.model.SecurityLog
import com.jayden.deviceadministration.app.model.TcpNetworkLog

class AdminLoggerFacade {
    fun mapSecurityLogs(
        logs: List<android.app.admin.SecurityLog.SecurityEvent>
    ): List<SecurityLog> = logs.map { log ->
        SecurityLog(
            logTimestampNanos = log.timeNanos,
            logLevel = log.logLevel,
            logTag = log.tag,
            logId = log.id,
            logData = log.data?.toString()
        )
    }

    fun filterTcpNetworkLogs(
        logs: List<android.app.admin.NetworkEvent>
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
        logs: List<android.app.admin.NetworkEvent>
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