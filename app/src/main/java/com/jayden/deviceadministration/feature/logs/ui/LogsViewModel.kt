package com.jayden.deviceadministration.feature.logs.ui

import androidx.lifecycle.ViewModel

class LogsViewModel(
    val repo: FooRepo
) : ViewModel() {
    fun bar() {
        repo.whatver()
    }
}