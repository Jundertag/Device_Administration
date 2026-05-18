package com.jayden.deviceadministration.app.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.jayden.deviceadministration.R
import com.jayden.deviceadministration.app.model.AppNotification

class AndroidNotificationFacade(
    val context: Context
) : NotificationFacade {
    private val manager: NotificationManager = context.getSystemService(NotificationManager::class.java)

    override fun ensureNotificationChannels() {
        val channels = listOf(
            NotificationChannel(
                NotificationConstants.ADMIN_FOREGROUND_SERVICE_CHANNEL_ID,
                NotificationConstants.ADMIN_FOREGROUND_SERVICE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ),
            NotificationChannel(
                NotificationConstants.ADMIN_PROVISIONING_SERVICE_CHANNEL_ID,
                NotificationConstants.ADMIN_PROVISIONING_SERVICE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            // add more channels...
        )

        manager.createNotificationChannels(channels)
    }

    override fun show(notification: AppNotification) {
        if (notification.id == null) throw NullPointerException("AppNotification.id must NOT be null")
        manager.notify(
            notification.id,
            Notification.Builder(context, notification.channelId).apply {
                setContentTitle(notification.title)
                setContentText(notification.body)
                setSmallIcon(R.drawable.ic_launcher_foreground)
            }.build()
        )
    }

    /**
     * note that [AppNotification.id] and [AppNotification.route] are ignored in this function.
     */
    @RequiresApi(Build.VERSION_CODES.S)
    override fun buildForegroundNotification(notification: AppNotification): Notification =
        Notification.Builder(context, notification.channelId).apply {
            setContentTitle(notification.title)
            setContentText(notification.body)
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setForegroundServiceBehavior(Notification.FOREGROUND_SERVICE_DEFAULT)
            setOngoing(true)
        }.build()

    override fun cancel(id: Int) {
        manager.cancel(id)
    }
}