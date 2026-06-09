package com.jayden.deviceadministration.app.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed interface AppDestination : NavKey {
    val route: String
    @Serializable
    data object Dashboard : AppDestination {
        override val route = "dashboard"
    }
    @Serializable
    data object Logs : AppDestination {
        override val route = "logs"
    }
    @Serializable
    data object TcpLogs: AppDestination {
        override val route = "logs/tcp"
    }
    @Serializable
    data object DnsLogs : AppDestination {
        override val route = "logs/dns"
    }
    @Serializable
    data object SecurityLogs : AppDestination {
        override val route = "logs/security"
    }
}