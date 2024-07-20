package com.github.kmpsandbox

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.github.kmpsandbox.ui.sayHallo
import com.github.kmpsandbox.ui.screen.RootScreen

fun main() {
    sayHallo()


    application {
        val state = rememberWindowState().apply {
            size = DpSize(500.dp, 600.dp)
        }
        Window(
            onCloseRequest = { exitApplication() },
            state = state,
            title = "KMP Sandbox"
        ) {
            RootScreen()
        }
    }
}