package com.jayden.deviceadministration.core.di

import androidx.room.Room
import com.jayden.deviceadministration.feature.dashboard.ui.AdminStateViewModel
import com.jayden.deviceadministration.core.database.AppDatabase
import com.jayden.deviceadministration.core.permissions.AndroidPermissionChecker
import com.jayden.deviceadministration.core.permissions.PermissionChecker
import com.jayden.deviceadministration.facade.AndroidNotificationFacade
import com.jayden.deviceadministration.facade.NotificationFacade
import com.jayden.deviceadministration.feature.dashboard.data.AdminStateMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object Modules {
    val appModule = module {
        single<AppDatabase> {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app-database").build()
        }
        single<PermissionChecker> { AndroidPermissionChecker(androidContext()) }
    }
}