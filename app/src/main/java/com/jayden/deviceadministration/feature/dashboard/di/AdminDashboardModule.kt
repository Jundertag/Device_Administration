package com.jayden.deviceadministration.feature.dashboard.di

import com.jayden.deviceadministration.core.di.Modules.appModule
import com.jayden.deviceadministration.feature.dashboard.data.AdminStateMonitor
import com.jayden.deviceadministration.feature.dashboard.ui.AdminStateViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val adminDashboardModule = module {
    includes(appModule)
    single<AdminStateMonitor> { AdminStateMonitor(androidContext()) }
    viewModel<AdminStateViewModel>()
}