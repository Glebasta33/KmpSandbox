package com.github.kmpsandbox.featuresample.mock

interface FeatureRepository {
    fun getData(): String
}

class MockFeatureRepository : FeatureRepository {
    override fun getData(): String {
        return "Hello from feature dependencies"
    }

}