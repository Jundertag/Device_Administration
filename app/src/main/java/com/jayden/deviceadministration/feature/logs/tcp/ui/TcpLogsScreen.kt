package com.jayden.deviceadministration.feature.logs.tcp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview(device = "id:pixel_9", showSystemUi = true, showBackground = true, apiLevel = 36)
@Composable
fun TcpLogsScreen(
    vm: TcpLogsViewModel = koinViewModel()
) {
    // here we go

}