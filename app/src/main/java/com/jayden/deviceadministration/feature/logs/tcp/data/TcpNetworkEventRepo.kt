package com.jayden.deviceadministration.feature.logs.tcp.data

import com.jayden.deviceadministration.feature.logs.tcp.domain.TcpNetworkEvent
import kotlinx.coroutines.flow.MutableStateFlow

class TcpNetworkEventRepo {
    private val _tcpLogFlow: MutableStateFlow<List<TcpNetworkEvent>> = MutableStateFlow(listOf())

    fun storeTcpLogs() {
        // TODO: store logs
    }

}