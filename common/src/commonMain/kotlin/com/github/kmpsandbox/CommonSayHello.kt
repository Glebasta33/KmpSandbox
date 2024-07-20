package com.github.kmpsandbox

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Данный код находится в src-сете commonMain.
 * Это значит, что он общий для всех платформенных таргетов,
 * которые используют commonMain в качестве sourceSet.
 */
fun sayHallo() {
    println("Hello from common code")
}

@Composable
fun SayHallo() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Hello from common source set!", modifier = Modifier.align(Alignment.Center))
    }
}