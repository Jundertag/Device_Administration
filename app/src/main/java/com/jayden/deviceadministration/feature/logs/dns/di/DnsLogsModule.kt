package com.jayden.deviceadministration.feature.logs.dns.di

import com.jayden.deviceadministration.core.database.AppDatabase
import com.jayden.deviceadministration.core.di.Modules.appModule
import com.jayden.deviceadministration.feature.logs.dns.data.DnsNetworkEventDao
import org.koin.dsl.module

val dnsLogsModule = module {
    includes(appModule)
    single<DnsNetworkEventDao> {
        get<AppDatabase>().dnsNetworkEventDao()
    }
}