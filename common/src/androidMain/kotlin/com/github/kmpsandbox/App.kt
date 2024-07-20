package com.github.kmpsandbox

import android.app.Application
import com.github.kmpsandbox.di.initKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}