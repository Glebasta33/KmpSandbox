rootProject.name = "KmpSandbox"

dependencyResolutionManagement {
    // Подключение репозиториев, откуда будут тянуться зависимости для подпроектов
    repositories {
        google()
        mavenCentral()
    }
}

include(":common")
include(":desktop")