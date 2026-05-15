package com.jayden.deviceadministration.app.model

import android.content.Intent

data class AppNotification(
    val id: Int,
    val channelId: String,
    val title: String,
    val body: String,
    val route: Intent?,
)