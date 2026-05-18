package com.jayden.deviceadministration.app.service

import android.app.admin.DeviceAdminService
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.jayden.deviceadministration.app.model.AppNotification
import com.jayden.deviceadministration.app.notification.NotificationConstants
import com.jayden.deviceadministration.app.notification.NotificationFacade
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdminService : DeviceAdminService(), KoinComponent {
    val facade: NotificationFacade by inject<NotificationFacade>()

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        initializeForeground()
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun initializeForeground() {
        val notification = facade.buildForegroundNotification(AppNotification(
            NotificationConstants.ADMIN_FOREGROUND_SERVICE_CHANNEL_ID,
            NotificationConstants.ADMIN_FOREGROUND_SERVICE_CHANNEL_NAME,
            "Admin Service is active"
        ))

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "startForeground()")

            startForeground(32767, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE)
        } else {
            Toast.makeText(this, "Notification permission not granted, background tasks will not be shown", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val TAG = "AdminService"
    }
}