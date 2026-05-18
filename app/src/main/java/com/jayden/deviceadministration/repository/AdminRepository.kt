package com.jayden.deviceadministration.repository

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.jayden.deviceadministration.app.model.AdministrationState
import com.jayden.deviceadministration.app.receiver.AdminReceiver
import com.jayden.deviceadministration.app.service.AdminService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AdminRepository(val context: Context) {
    private val adminReceiver = ComponentName(context, AdminReceiver::class.java)
    private val dpm = context.getSystemService(DevicePolicyManager::class.java)

    private val _adminState = MutableStateFlow(AdministrationState(
        adminGranted = dpm.isAdminActive(adminReceiver),
        deviceOwner = dpm.isDeviceOwnerApp(context.packageName),
        profileOwner = dpm.isProfileOwnerApp(context.packageName),
        isProvisioningAllowed = dpm.isProvisioningAllowed(DevicePolicyManager.ACTION_PROVISION_MANAGED_PROFILE)
    ))

    val adminState = _adminState.asStateFlow()

    fun onAdminStatusChanged() {
        _adminState.update {
            it.copy(
                adminGranted = dpm.isAdminActive(adminReceiver),
                deviceOwner = dpm.isDeviceOwnerApp(context.packageName),
                profileOwner = dpm.isProfileOwnerApp(context.packageName)
            )
        }
    }
}