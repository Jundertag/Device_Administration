package com.jayden.deviceadministration.di

import androidx.room.Room
import com.jayden.deviceadministration.app.viewmodel.MainViewModel
import com.jayden.deviceadministration.data.room.network.NetworkLogDatabase
import com.jayden.deviceadministration.data.room.security.SecurityLogDatabase
import com.jayden.deviceadministration.facade.AdminLoggerFacade
import com.jayden.deviceadministration.facade.AndroidNotificationFacade
import com.jayden.deviceadministration.facade.NotificationFacade
import com.jayden.deviceadministration.repository.AdminLoggerRepository
import com.jayden.deviceadministration.repository.AdminRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object Modules {
    val appModule = module {
        single<SecurityLogDatabase> {
            Room.databaseBuilder(
                androidContext(),
                SecurityLogDatabase::class.java, "security-log-database"
            ).build()
        }
        single<NetworkLogDatabase> {
            Room.databaseBuilder(
                androidContext(),
                NetworkLogDatabase::class.java, "network-log-database"
            ).build()
        }
        single<AdminRepository> { AdminRepository(androidContext()) }
        single<AdminLoggerRepository> { AdminLoggerRepository(
            get(),
            get()
        ) }
        single<AdminLoggerFacade> { AdminLoggerFacade() }
        single<NotificationFacade> { AndroidNotificationFacade(androidContext()) }
        viewModel<MainViewModel> { MainViewModel(get()) }
    }
}