package com.jayden.deviceadministration.app.model

import android.content.Intent

data class AppNotification(
    val channelId: String,
    val title: String,
    val body: String,
    val id: Int? = null,
    val route: Intent? = null,
)