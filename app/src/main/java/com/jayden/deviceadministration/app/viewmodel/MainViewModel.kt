package com.jayden.deviceadministration.app.viewmodel

import androidx.lifecycle.ViewModel
import com.jayden.deviceadministration.repository.status.AdminStatusRepository

class MainViewModel(
    val repo: AdminStatusRepository
) : ViewModel() {
    val adminStatus = repo.adminState

    fun refreshAdminStatus() {
        repo.onAdminStatusChanged()
    }

    fun getProfileOwnerNotAllowedReason(): String {
        repo
    }
}