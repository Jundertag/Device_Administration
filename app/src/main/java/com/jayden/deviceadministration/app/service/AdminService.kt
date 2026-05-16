package com.jayden.deviceadministration.app.service

import android.app.admin.DeviceAdminService
import android.content.Intent
import com.jayden.deviceadministration.app.model.AppNotification
import com.jayden.deviceadministration.app.notification.NotificationConstants
import com.jayden.deviceadministration.facade.NotificationFacade
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdminService : DeviceAdminService(), KoinComponent {
    val facade: NotificationFacade by inject<NotificationFacade>()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        initializeForeground()
        return START_STICKY
    }

    private fun initializeForeground() {
        val notification = facade.buildForegroundNotification(AppNotification(
            NotificationConstants.ADMIN_FOREGROUND_SERVICE_CHANNEL_ID,
            NotificationConstants.ADMIN_FOREGROUND_SERVICE_CHANNEL_NAME,
            "Admin Service is active"
        ))

        startForeground(32767, notification)
    }
}