plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    jvm()

    // в sourceSets указываются зависимости для таргетов в текущем модуле
    // sourceSet - источник зависимостей.
    // таргет - место, где будут использоваться зависимости.
    // в sourceSets можно указать разные зависимости для конкретный таргетов.
    sourceSets {
        // указываем зависимости для таргета commonMain.
        // эти зависимости станут доступны для всех таргетов, которые используют commonMain в качестве sourceSet.
        commonMain {
            dependencies {
                //Compose станет общим для всех таргетов, которые используют commonMain в качестве sourceSet.
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
            }
        }

        jvmMain {
            dependencies {
                // api - делает зависимость транзитивной,
                // т.е. доступ к этой зависимости будет у модуля, у которого есть зависимость на shared
                // Данная зависимость предоставляет application{} и Window, в которых можно вызвать Compose UI.
                api(compose.desktop.currentOs)
            }
        }
    }
}