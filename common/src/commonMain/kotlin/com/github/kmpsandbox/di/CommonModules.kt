package com.github.kmpsandbox.di

import com.github.kmpsandbox.utils.DeviceInfo
import org.koin.dsl.module

object CommonModules {
    val deviceInfo = module {
        single { DeviceInfo() }
    }
}