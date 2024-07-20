package com.github.kmpsandbox.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.kmpsandbox.di.getKoinInstance
import com.github.kmpsandbox.featuresample.mock.FeatureRepository
import com.github.kmpsandbox.featuresample.ui.FeatureScreen
import com.github.kmpsandbox.ui.deviceinfo.DeviceInfoCell

@Composable
fun RootScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeviceInfoCell(getKoinInstance())
        val featureRepository: FeatureRepository = getKoinInstance()
        FeatureScreen(featureRepository.getData())
    }

}