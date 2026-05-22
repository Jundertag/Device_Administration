package com.jayden.deviceadministration.feature.logs.ui

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.jayden.deviceadministration.feature.logs.dns.ui.DnsLogsScreen
import com.jayden.deviceadministration.feature.logs.security.ui.SecurityLogsScreen
import com.jayden.deviceadministration.feature.logs.tcp.ui.TcpLogsScreen

enum class LogsPage {
    DNS,
    TCP,
    SECURITY
}

@Composable
fun LogsScreen(
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { LogsPage.entries.size }
    )

    HorizontalPager(
        state = pagerState
    ) { page ->
        when (LogsPage.entries[page]) {
            LogsPage.DNS -> DnsLogsScreen()
            LogsPage.TCP -> TcpLogsScreen()
            LogsPage.SECURITY -> SecurityLogsScreen()
        }
    }
}