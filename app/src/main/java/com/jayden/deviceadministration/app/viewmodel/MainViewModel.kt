package com.jayden.deviceadministration.app.viewmodel

import androidx.lifecycle.ViewModel
import com.jayden.deviceadministration.app.model.AdministrationState
import com.jayden.deviceadministration.repository.AdminRepository
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    repo: AdminRepository
) : ViewModel() {
    val adminStatus = repo.adminState
}