package com.jayden.deviceadministration.facade

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.jayden.deviceadministration.app.model.AppNotification
import com.jayden.deviceadministration.app.notification.NotificationConstants

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
            ).apply {

            },
            // add more channels...
        )

        manager.createNotificationChannels(channels)
    }

    override fun show(notification: AppNotification) {
        manager.notify(
            notification.id,
            Notification.Builder(context, notification.channelId).apply {
                setContentTitle(notification.title)
                setContentText(notification.body)
            }.build()
        )
    }

    override fun cancel(id: Int) {
        manager.cancel(id)
    }
}