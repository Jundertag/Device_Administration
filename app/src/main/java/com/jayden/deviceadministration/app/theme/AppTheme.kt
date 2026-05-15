package com.jayden.deviceadministration.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

object AppTheme {
    @Composable
    fun UseAppTheme(
        content: @Composable () -> Unit
    ) {
        val colorScheme = if (isSystemInDarkTheme()) darkColorScheme(
            // set primary, secondary, tertiary, surface, and background colors
        ) else lightColorScheme(
            // set primary, secondary, tertiary, surface, and background colors
        )

        MaterialTheme(
            colorScheme = colorScheme
        ) {
            content()
        }
    }
}