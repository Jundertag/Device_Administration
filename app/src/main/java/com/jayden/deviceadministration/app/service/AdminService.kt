package com.jayden.deviceadministration.app.service

import android.app.admin.DeviceAdminService
import android.widget.Toast
import org.koin.core.component.KoinComponent

class AdminService : DeviceAdminService(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "test message", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "AdminService"
    }
}