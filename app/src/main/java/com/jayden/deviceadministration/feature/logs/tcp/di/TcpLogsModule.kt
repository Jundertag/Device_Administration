package com.jayden.deviceadministration.feature.logs.tcp.di

import com.jayden.deviceadministration.core.database.AppDatabase
import com.jayden.deviceadministration.core.di.Modules.appModule
import com.jayden.deviceadministration.feature.logs.tcp.data.TcpNetworkEventDao
import com.jayden.deviceadministration.feature.logs.tcp.data.TcpNetworkEventRepo
import com.jayden.deviceadministration.feature.logs.ui.LogsViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val tcpLogsModule = module {
    includes(appModule)
    single<TcpNetworkEventDao> {
        get<AppDatabase>().tcpNetworkEventDao()
    }
    single<TcpNetworkEventRepo> { TcpNetworkEventRepo(
        dao = get()
    ) }
    viewModel<LogsViewModel>()
}