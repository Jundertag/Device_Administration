package com.jayden.deviceadministration.app.service

import android.app.Notification
import android.app.admin.DeviceAdminService
import android.content.Intent

class AdminService : DeviceAdminService() {
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        initializeForeground()
        return START_STICKY
    }

    private fun initializeForeground() {
        val serviceNotification = Notification.Builder(this, ADMIN_FOREGROUND_SERVICE_CHANNEL_ID).apply {
            setContentTitle("This profile is being managed by this app")
            setContentText("Click for details on status.")
        }
    }

    companion object {

    }
}