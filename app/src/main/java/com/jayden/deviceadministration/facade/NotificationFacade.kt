package com.jayden.deviceadministration.facade

import com.jayden.deviceadministration.app.model.AppNotification

interface NotificationFacade {
    fun ensureNotificationChannels()
    fun show(notification: AppNotification)
    fun cancel(id: Int)
}