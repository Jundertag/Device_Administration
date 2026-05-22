package com.jayden.deviceadministration.feature.dashboard.ui

import androidx.lifecycle.ViewModel
import com.jayden.deviceadministration.feature.dashboard.data.AdminStateMonitor

class AdminStateViewModel(
    val repo: AdminStateMonitor
) : ViewModel() {
    val adminStatus = repo.adminState

    fun refreshAdminStatus() {
        repo.refreshAdminStatus()
    }
}