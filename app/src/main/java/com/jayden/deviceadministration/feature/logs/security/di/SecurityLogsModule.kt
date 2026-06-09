package com.jayden.deviceadministration.feature.logs.security.di

import com.jayden.deviceadministration.core.database.AppDatabase
import com.jayden.deviceadministration.core.di.Modules.appModule
import com.jayden.deviceadministration.feature.logs.security.data.SecurityLogDao
import org.koin.dsl.module

val securityLogsModule = module {
    includes(appModule)
    single<SecurityLogDao> {
        get<AppDatabase>().securityLogDao()
    }
}