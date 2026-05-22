package com.jayden.deviceadministration.feature.dashboard.ui

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jayden.deviceadministration.app.model.AdministrationState
import com.jayden.deviceadministration.app.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AdminDashboardScreen(
    modifier: Modifier = Modifier,
    vm: MainViewModel = koinViewModel(),
    onRequestAdminPermission: () -> Unit,
    onRequestProfileOwner: () -> Unit,
    onRequestDeviceOwner: () -> Unit,
) {
    val adminState by vm.adminStatus.collectAsStateWithLifecycle()
    var adminGrantChooseDialogVisible by remember { mutableStateOf(false) }

    if (adminGrantChooseDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                adminGrantChooseDialogVisible = false
            },
            title = { Text("Choose Grant Level") },
            text = {
                Column {
                    TextButton(
                        onClick = {
                            adminGrantChooseDialogVisible = false
                            onRequestAdminPermission()
                        }
                    ) {
                        Text("Grant Admin")
                    }
                    TextButton(
                        onClick = {
                            adminGrantChooseDialogVisible = false
                            onRequestProfileOwner()
                        }
                    ) {
                        Text("Grant Profile Owner (of new work profile)")
                    }
                    TextButton(
                        onClick = {
                            adminGrantChooseDialogVisible = false
                            onRequestDeviceOwner()
                        }
                    ) {
                        Text("Grant Device Owner")
                    }
                }
            },
            confirmButton = {

            }
        )
    }

    LaunchedEffect(Unit) {
        vm.refreshAdminStatus()
    }

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        AdminStatusCard(
            modifier = Modifier.padding(innerPadding),
            title = "Admin Status",
            state = adminState,
            onLaunchAdminGrantChooseDialog = {
                adminGrantChooseDialogVisible = true
            }
        )
    }
}

@Composable
fun AdminStatusCard(
    modifier: Modifier = Modifier,
    title: String,
    state: AdministrationState,
    onLaunchAdminGrantChooseDialog: () -> Unit
) {
    ElevatedCard(
        modifier = modifier.combinedClickable(
            onClick = {},
            onLongClick = {
                onLaunchAdminGrantChooseDialog()
            }
        )
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)

        Text(text = "Admin Granted: " + state.adminGranted.toString(),
            style = MaterialTheme.typography.labelMedium
        )
        Text(text = "Profile Owner: " + state.profileOwner.toString(),
            style = MaterialTheme.typography.labelMedium
        )
        Text(text = "Device Owner: " + state.deviceOwner.toString(),
            style = MaterialTheme.typography.labelMedium
        )
    }
}