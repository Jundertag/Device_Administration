package com.jayden.deviceadministration.app.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import com.jayden.deviceadministration.app.theme.AppTheme
import com.jayden.deviceadministration.app.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // yay some compose content
            Surface {
                AppTheme.setAppTheme()

            }
        }
    }
}