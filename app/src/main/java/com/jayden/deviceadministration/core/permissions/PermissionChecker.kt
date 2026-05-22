package com.jayden.deviceadministration.core.permissions

interface PermissionChecker {
    fun getPermissionGrantStatus(permission: String): PermissionState
}