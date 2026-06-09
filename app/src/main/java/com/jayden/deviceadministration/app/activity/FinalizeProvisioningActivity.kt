package com.jayden.deviceadministration.app.activity

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jayden.deviceadministration.app.receiver.AdminReceiver
import org.koin.core.component.KoinComponent

class FinalizeProvisioningActivity : AppCompatActivity(), KoinComponent {
    val adminReceiverName by lazy { ComponentName(applicationContext, AdminReceiver::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.action == DevicePolicyManager.ACTION_PROVISIONING_SUCCESSFUL) {
            Log.i(TAG, "Profile Provisioning Successful")
            val dpm = applicationContext.getSystemService(DevicePolicyManager::class.java)

            if (dpm.isProfileOwnerApp(packageName)) {
                Log.d(TAG, "Verified Profile owner, finalizing provisioning")

                packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_PERMISSIONS
                ).permissions?.forEach { perm ->
                    if (perm.protection == PermissionInfo.PROTECTION_DANGEROUS) {
                        dpm.setPermissionGrantState(
                            adminReceiverName, packageName, perm.name,
                            DevicePolicyManager.PERMISSION_GRANT_STATE_GRANTED
                        )
                    }
                }

                dpm.setProfileName(adminReceiverName, "Device Administration")
                dpm.setProfileEnabled(adminReceiverName)
            } else {
                Log.e(TAG, "ERROR: Profile Owner NOT VERIFIED, cannot finalize provisioning")
            }
        }
        finish()
    }
    companion object {
        private const val TAG = "FinalizeProvisioningActivity"
        const val FINALIZE_PROVISION = "app.action.FINALIZE_PROVISION"
    }
}