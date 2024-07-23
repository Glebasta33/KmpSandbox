package com.github.kmpsandbox.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.github.kmpsandbox.featuresample.presentation.root.RootComponentImpl
import com.github.kmpsandbox.ui.screen.RootScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(
            componentContext = defaultComponentContext()
        )
        setContent {
            RootScreen(rootComponent)
        }
    }
}