package com.github.kmpsandbox

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.github.kmpsandbox.di.initKoin
import com.github.kmpsandbox.featuresample.presentation.root.RootComponentImpl
import com.github.kmpsandbox.ui.sayHallo
import com.github.kmpsandbox.ui.screen.RootScreen
import kotlinx.coroutines.Dispatchers

fun main() {
    overrideSchedulers(main = Dispatchers.Main::asScheduler)
    sayHallo()

    initKoin()



    application {
        val state = rememberWindowState().apply {
            size = DpSize(500.dp, 600.dp)
        }

        val lifecycle = LifecycleRegistry()
        val defaultComponentContext = DefaultComponentContext(lifecycle)
        val rootComponent = RootComponentImpl(defaultComponentContext)

        Window(
            onCloseRequest = { exitApplication() },
            state = state,
            title = "KMP Sandbox"
        ) {
            RootScreen(rootComponent)
        }
    }
}