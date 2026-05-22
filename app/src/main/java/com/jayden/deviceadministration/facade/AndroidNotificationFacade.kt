package com.jayden.deviceadministration.facade

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.jayden.deviceadministration.R
import com.jayden.deviceadministration.app.model.AppNotification
import com.jayden.deviceadministration.core.notifications.NotificationConstants

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
        }.build().also {
            Log.v(TAG, "buildForegroundNotification(\n" +
                    "    channelId: ${notification.channelId}\n" +
                    "    title: ${notification.title}\n" +
                    "    body: ${notification.body}\n" +
                    "    id: ${notification.id}\n" +
                    "    route: ${notification.route?.action}\n" +
                    ")")
        }

    override fun cancel(id: Int) {
        manager.cancel(id)
    }

    companion object {
        private const val TAG = "AndroidNotificationFacade"
    }
}