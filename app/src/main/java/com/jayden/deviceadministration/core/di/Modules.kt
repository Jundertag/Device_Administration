package com.jayden.deviceadministration.core.di

import androidx.room.Room
import com.jayden.deviceadministration.app.viewmodel.MainViewModel
import com.jayden.deviceadministration.core.database.AppDatabase
import com.jayden.deviceadministration.facade.AdminLoggerFacade
import com.jayden.deviceadministration.facade.AndroidNotificationFacade
import com.jayden.deviceadministration.facade.NotificationFacade
import com.jayden.deviceadministration.repository.AdminLoggerRepository
import com.jayden.deviceadministration.repository.AdminRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object Modules {
    val appModule = module {
        single<AppDatabase> {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app-database").build()
        }
        single<AdminRepository> { AdminRepository(androidContext()) }
        single<AdminLoggerRepository> { AdminLoggerRepository(
            get()
        ) }
        single<AdminLoggerFacade> { AdminLoggerFacade() }
        single<NotificationFacade> { AndroidNotificationFacade(androidContext()) }
        viewModel<MainViewModel> { MainViewModel(get()) }
    }
}