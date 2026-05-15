package com.jayden.deviceadministration.app.activity.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jayden.deviceadministration.app.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AdminDashboardScreen(
    modifier: Modifier = Modifier,
    vm: MainViewModel = koinViewModel()
) {

}


@Composable
fun StatusCard(
    modifier: Modifier = Modifier,
    title: String,
    items: List<StatusItem>
) {
    ElevatedCard {
        Text(title)

        items.forEach { item ->
            Row { Text(item.title); Text(item.status.toString()) }
        }
    }
}

data class StatusItem(val title: String, val status: Boolean)