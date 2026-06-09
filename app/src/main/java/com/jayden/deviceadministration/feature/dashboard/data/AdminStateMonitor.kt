package com.jayden.deviceadministration.feature.dashboard.data

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import com.jayden.deviceadministration.feature.dashboard.domain.AdministrationState
import com.jayden.deviceadministration.app.receiver.AdminReceiver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AdminStateMonitor(
    private val context: Context
) {
    private val adminReceiver = ComponentName(context, AdminReceiver::class.java)
    private val dpm = context.getSystemService(DevicePolicyManager::class.java)

    private val _adminState = MutableStateFlow(
        readAdminState()
    )

    val adminState = _adminState.asStateFlow()

    fun refreshAdminStatus() {
        _adminState.value = readAdminState()
    }

    private fun readAdminState(): AdministrationState = AdministrationState(
        adminGranted = dpm.isAdminActive(adminReceiver),
        deviceOwner = dpm.isDeviceOwnerApp(context.packageName),
        profileOwner = dpm.isProfileOwnerApp(context.packageName)
    )
}