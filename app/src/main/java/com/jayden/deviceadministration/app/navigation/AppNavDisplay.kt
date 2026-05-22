package com.jayden.deviceadministration.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jayden.deviceadministration.feature.dashboard.ui.AdminDashboardScreen
import com.jayden.deviceadministration.feature.logs.dns.ui.DnsLogsScreen
import com.jayden.deviceadministration.feature.logs.security.ui.SecurityLogsScreen
import com.jayden.deviceadministration.feature.logs.tcp.ui.TcpLogsScreen
import com.jayden.deviceadministration.feature.logs.ui.LogsScreen

@Composable
fun AppNavDisplay() {
    val backStack = rememberNavBackStack(AppDestination.Dashboard)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<AppDestination.Dashboard> {
                AdminDashboardScreen(
                    onRequestAdminPermission = {
                        // TODO
                    },
                    onRequestProfileOwner = {
                        // TODO
                    },
                    onRequestDeviceOwner = {
                        // TODO
                    }
                )
            }
            entry<AppDestination.Logs> {
                LogsScreen(
                    onNavigateToDnsLogsScreen = {
                        // TODO
                    },
                    onNavigateToSecurityLogsScreen = {
                        // TODO
                    },
                    onNavigateToTcpLogsScreen = {
                        // TODO
                    }
                )
            }
        },
        onBack = {
            backStack.removeLastOrNull()
        }
    )
}