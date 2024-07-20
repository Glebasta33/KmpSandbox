package com.github.kmpsandbox.di

import com.github.kmpsandbox.utils.DeviceInfo
import org.koin.dsl.module

object CoreModule {
    val deviceInfo = module {
        single { DeviceInfo() }
    }
}