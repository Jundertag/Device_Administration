package com.jayden.deviceadministration.app.activity

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jayden.deviceadministration.app.activity.screens.AdminDashboardScreen
import com.jayden.deviceadministration.app.receiver.AdminReceiver
import com.jayden.deviceadministration.app.service.AdminService
import com.jayden.deviceadministration.app.service.ProvisioningForegroundService
import com.jayden.deviceadministration.app.theme.AppTheme
import com.jayden.deviceadministration.app.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    val viewModel: MainViewModel by viewModel()

    private val adminReceiver by lazy { ComponentName(applicationContext, AdminReceiver::class.java) }

    val addAdminResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                Log.i(TAG, "Admin permission granted")
                viewModel.refreshAdminStatus()
            }
            RESULT_CANCELED -> {
                Log.i(TAG, "Admin permission denied")
            }
        }
    }

    val provisionManagedProfileResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                Log.i(TAG, "Profile Created with this app as owner")
                viewModel.refreshAdminStatus()
            }
            RESULT_CANCELED -> {
                Log.i(TAG, "Profile creation unsuccessful")
            }
        }
        stopService(Intent(this, ProvisioningForegroundService::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.adminStatus.value.adminGranted) {
            startService(Intent(applicationContext, AdminService::class.java))
        }
        setContent {
            AppTheme.UseAppTheme {


                AdminDashboardScreen(
                    modifier = Modifier.fillMaxSize(),
                    vm = viewModel,
                    onRequestAdminPermission = {
                        addAdminResultLauncher.launch(Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                            putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminReceiver)
                            putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "We need administrator permissions to execute some of our app functions.")
                        })
                    },
                    onRequestProfileOwner = {
                        if (viewModel.adminStatus.value.isProvisioningAllowed) {
                            provisionManagedProfileResultLauncher.launch(Intent(DevicePolicyManager.ACTION_PROVISION_MANAGED_PROFILE).apply {
                                putExtra(
                                    DevicePolicyManager.EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME,
                                    adminReceiver
                                )
                            })
                        } else {
                            Toast.makeText(this, "Unable to Provision new profile: Provisioning not allowed", Toast.LENGTH_LONG).show()
                        }
                        startService(Intent().apply { setClass(applicationContext,
                            ProvisioningForegroundService::class.java) })
                    },
                    onRequestDeviceOwner = {
                        Toast.makeText(this, "Feature not available yet", Toast.LENGTH_SHORT).show()
                    },
                )
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}