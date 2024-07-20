package com.github.kmpsandbox.featuresample.di

import com.github.kmpsandbox.featuresample.mock.FeatureRepository
import com.github.kmpsandbox.featuresample.mock.MockFeatureRepository
import org.koin.dsl.bind
import org.koin.dsl.module

object FeatureSampleModule {
    val repository = module {
        single { MockFeatureRepository() }.bind(FeatureRepository::class)
    }
}