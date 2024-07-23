plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.android.library) //плагин android-библиотеки
}

/**
 * ## Targets.
 * Таргеты задают платформы, для которых будет компилиться common-code.
 * Kotlin target - это идентификатор, описывающий цель компиляции.
 * Таргеты - это "инструкции" для компилятора, описывающие формат конечных бинарников для платформ.
 *
 * Внутри плагина kotlin перечисляется набор таргетов-платформ приложения.
 * Common code будет скомпилирован отдельно для каждого таргета.
 *
 * ## Source sets.
 * Kotlin source set - это набор исходных файлов, зависимостей.
 * Это основной способ передачи кода в мультиплатформенном проекте.
 * Каждый source set:
 * - Имеет уникальное имя.
 * - Содержит файлы кода или ресурсов, обычно хранящихся в директории с именем source set`а.
 * - Задаёт набор таргетов, для которых будет компилироваться код из данного source set`а.
 * - Задаёт собственные dependencies и compiler options.
 *
 * commonMain - один из предустановленный source set`ов, который компилируется во все указанные таргеты.
 * Source sets - это директории внутри src.
 * Platform-specific source sets предоставляют зависимости для конкретных платформенных таргетов,
 * код из данного source set компилируется только для соответсвующего платформенного таргета.
 * Внутри Platform-specific source sets можно использовать код из commonMain (но не наоборот!).
 *
 * ## Intermediate source sets.
 * Intermediate source sets - это нечто среднее между commonMain и платформенным таргетом (например, androidMain, jmvMain).
 * Это common-код специфичный для конкретных платформ
 */
kotlin {
    jvm() // 1 таргет jvm для desktop-платформы
    androidTarget() // 2 таргет для android-платформы

    // в sourceSets можно указать зависимости для source set`ов.
    sourceSets {
        // указываем зависимости для source set`а commonMain.
        // эти зависимости станут доступны для всех таргетов, которые используют commonMain в качестве sourceSet.
        val commonMain by getting { //by getting используется для создания переменной, которую можно использовать в dependsOn платформенных таргетов.
            dependencies {
                //Compose станет общим для всех таргетов, которые используют commonMain в качестве sourceSet.
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                //DI
                api(libs.koin.core)

                //MVI
                api(libs.mvikotlin.core)
                api(libs.mvikotlin.main)
                api(libs.mvikotlin.coroutines)

                //Navigation
                api(libs.decompose.core)
                api(libs.decompose.compose)

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")

                api(project(":feature-sample")) //транзитивно передаю зависимость на фичу
            }
        }

        /**
         * В то время как common-код может компилироваться во все платформенные таргеты,
         * существуют platform-specific APIs, которые нельзя использовать в common-source set.
         * Platform-specific source sets предоставляют зависимости для конкретных платформенных таргетов.
         * Например, jvmMain source set компилируется только для jvm таргета.
         */
        jvmMain {
            dependsOn(commonMain)
            dependencies {
                // api - делает зависимость транзитивной,
                // т.е. доступ к этой зависимости будет у модуля, у которого есть зависимость на shared
                // Данная зависимость предоставляет application{} и Window, в которых можно вызвать Compose UI.
                api(compose.desktop.currentOs)
            }
        }
        androidMain {
            dependsOn(commonMain)
        }
    }
}

// Android-плагин:
android {
    namespace = "com.github.kmpsandbox"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}