package com.jayden.deviceadministration.app.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.jayden.deviceadministration.app.model.AppNotification
import com.jayden.deviceadministration.app.notification.NotificationConstants
import com.jayden.deviceadministration.app.notification.NotificationFacade
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProvisioningForegroundService : Service(), KoinComponent {
    val facade: NotificationFacade by inject()
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            ACTION_STOP -> {
                stopSelf()
                return START_NOT_STICKY
            }
        }

        initializeForeground()
        return START_NOT_STICKY
    }

    private fun initializeForeground() {
        facade.ensureNotificationChannels()

        val notification = facade.buildForegroundNotification(
            AppNotification(
                NotificationConstants.ADMIN_PROVISIONING_SERVICE_CHANNEL_ID,
                "Waiting until Provisioning flow is complete.",
                "Provisioning requires listening to intents that are only broadcast when the app is in the foreground."
            )
        )


        startForeground(32768, notification)
        Log.v(TAG, "Starting Foreground Service Mode")
        val provisioningReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

            }
        }
    }

    override fun onDestroy() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        Log.d(TAG, "onDestroy()")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    companion object {
        private const val TAG = "ProvisioningForegroundService"
        const val ACTION_STOP = "com.jayden.deviceadministration.action.STOP"
    }
}