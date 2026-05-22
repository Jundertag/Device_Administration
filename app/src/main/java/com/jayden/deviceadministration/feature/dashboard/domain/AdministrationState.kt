package com.jayden.deviceadministration.feature.dashboard.domain

data class AdministrationState(
    val adminGranted: Boolean,
    val deviceOwner: Boolean,
    val profileOwner: Boolean,
)