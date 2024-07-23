plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    jvm("desktop") // позволяет более точно указать название платформы: вместо jvmMain будет desktopMain

    // Подключаем мультиплатформенный common-код для текущей платформы
    // Иначе из desktop не будет доступа к коду из common-модуля
    sourceSets {
        getByName("desktopMain") {// название таргета (текущей платформы), к которой мы подключаем модуль common в качестве sourceSet (а следовательно и все его sourceSet`ы).
            dependencies { // подключаем зависимость на common-код
                implementation(project(":common"))
                implementation(libs.badoo.reaktive)
                implementation(libs.badoo.reaktive.coroutines.interop)
                implementation(libs.coroutines.swing)
            }
        }
    }
}

// Чтобы Compose работал на Desktop, нужно сконфигурировать точку входа:
compose.desktop {
    application {
        mainClass = "MainKt"
    }
}