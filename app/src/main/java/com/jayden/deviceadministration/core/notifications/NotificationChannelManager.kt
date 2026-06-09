package com.jayden.deviceadministration.core.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class NotificationChannelManager(
    val manager: NotificationManager
) {
    fun ensureNotificationChannels() {
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
}