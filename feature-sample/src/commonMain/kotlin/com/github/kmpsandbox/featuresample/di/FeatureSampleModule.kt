package com.github.kmpsandbox.featuresample.di

import com.github.kmpsandbox.featuresample.mock.FeatureRepository
import com.github.kmpsandbox.featuresample.mock.MockFeatureRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object FeatureSampleModule {
    val repository = module {
        singleOf(::MockFeatureRepository) bind FeatureRepository::class
    }
}