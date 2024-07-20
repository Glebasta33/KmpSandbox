package com.github.kmpsandbox.ui.deviceinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.kmpsandbox.utils.DeviceInfo
import com.github.kmpsandbox.utils.randomUUID

@Composable
fun DeviceInfoCell() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = DeviceInfo().getSummary())
        Text(text = "Random UUID: ${randomUUID()}")
    }
}