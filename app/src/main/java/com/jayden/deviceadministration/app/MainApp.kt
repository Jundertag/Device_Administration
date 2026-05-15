package com.jayden.deviceadministration.app

import android.app.Application
import com.jayden.deviceadministration.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            modules(Modules.appModule)
        }
    }
}