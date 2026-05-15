package com.jayden.deviceadministration.app.model

data class AdministrationState(
    val adminGranted: Boolean,
    val deviceOwner: Boolean,
    val profileOwner: Boolean,
)