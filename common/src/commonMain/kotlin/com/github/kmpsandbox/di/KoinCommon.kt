package com.github.kmpsandbox.di

import com.github.kmpsandbox.featuresample.di.FeatureSampleModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.qualifier.Qualifier

//для быстрого получения зависимости из графа по типу
inline fun <reified T> getKoinInstance(qualifier: Qualifier? = null): T {
    return object : KoinComponent {
        val value : T by inject()
    }.value
}

fun initKoin() = startKoin {
    modules(
        CommonModules.deviceInfo,
        FeatureSampleModule.repository
    )
}