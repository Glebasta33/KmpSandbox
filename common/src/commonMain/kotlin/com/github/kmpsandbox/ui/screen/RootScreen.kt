package com.github.kmpsandbox.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.kmpsandbox.featuresample.presentation.root.RootComponent
import com.github.kmpsandbox.featuresample.presentation.root.RootContent

@Composable
fun RootScreen(rootComponent: RootComponent) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        DeviceInfoCell(getKoinInstance())
//        val featureRepository: FeatureRepository = getKoinInstance()
//        FeatureScreen(featureRepository.getData())

        RootContent(rootComponent)
    }

}