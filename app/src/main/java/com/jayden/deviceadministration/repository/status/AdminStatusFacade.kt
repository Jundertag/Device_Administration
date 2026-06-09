package com.jayden.deviceadministration.repository.status

import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.UserManager

class AdminStatusFacade(
    val context: Context
) {
    val userManager = context.getSystemService(UserManager::class.java)
    val dpm = context.getSystemService(DevicePolicyManager::class.java)

    /**
     * The logic is: if the user has a profile already associated with that user, then that is likely
     * a managed profile and should be investigated, but this is only a signal, it is not authoritative.
     *
     * Plus, if a user already has a profile, then it's not possible to create another profile anyway.
     */
    fun doesManagedProfileLikelyExist(): Boolean = userManager.userProfiles.size > 1

    fun isManagedUsersFeatureAvailable(): Boolean = context.packageManager.hasSystemFeature(
        PackageManager.FEATURE_MANAGED_USERS
    )

    /**
     * @return `true` when context user is a managed profile and [Build.VERSION.SDK_INT] >= [Build.VERSION_CODES.R], otherwise false.
     */
    fun isContextUserAManagedProfile(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (userManager.isManagedProfile) return true
        }
        // cannot check if context user is a profile, assuming false.
        return false
    }
}