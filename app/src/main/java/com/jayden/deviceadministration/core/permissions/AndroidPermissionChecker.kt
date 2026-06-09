package com.jayden.deviceadministration.core.permissions

import android.content.Context
import android.content.pm.PackageManager

class AndroidPermissionChecker(
    val context: Context
) : PermissionChecker {
    override fun getPermissionGrantStatus(permission: String): PermissionState {
        return when (val result = context.checkSelfPermission(permission)) {
            PackageManager.PERMISSION_GRANTED -> PermissionState.GRANTED
            PackageManager.PERMISSION_DENIED -> PermissionState.DENIED
            else -> throw IllegalStateException("Unknown permission result $result")
        }
    }
}