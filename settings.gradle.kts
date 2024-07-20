rootProject.name = "KmpSandbox"

dependencyResolutionManagement {
    // Подключение репозиториев, откуда будут тянуться зависимости для подпроектов
    repositories {
        google()
        mavenCentral()
    }
}

// Для Android
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

include(":common")
include(":desktop")
include(":android")