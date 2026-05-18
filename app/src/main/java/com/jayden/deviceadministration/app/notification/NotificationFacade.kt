package com.jayden.deviceadministration.app.notification

import android.app.Notification
import com.jayden.deviceadministration.app.model.AppNotification

interface NotificationFacade {
    fun ensureNotificationChannels()
    fun show(notification: AppNotification)
    fun buildForegroundNotification(notification: AppNotification): Notification
    fun cancel(id: Int)
}