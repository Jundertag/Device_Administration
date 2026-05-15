package com.jayden.deviceadministration.app.activity.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jayden.deviceadministration.app.model.AdministrationState
import com.jayden.deviceadministration.app.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AdminDashboardScreen(
    modifier: Modifier = Modifier,
    vm: MainViewModel = koinViewModel()
) {
    val adminState by vm.adminStatus.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        AdminStatusCard(
            modifier = Modifier.padding(innerPadding),
            title = "Admin Status",
            adminState
        )
    }
}

@Composable
fun AdminStatusCard(
    modifier: Modifier = Modifier,
    title: String,
    state: AdministrationState
) {
    ElevatedCard(modifier = modifier) {
        Text(title, style = MaterialTheme.typography.headlineMedium)

        Text(text = "Admin Granted: " + state.adminGranted.toString(),
            style = MaterialTheme.typography.labelSmall
        )
        Text(text = "Profile Owner: " + state.profileOwner.toString(),
            style = MaterialTheme.typography.labelSmall
        )
        Text(text = "Device Owner: " + state.deviceOwner.toString(),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

data class StatusItem(val title: String, val status: Boolean)