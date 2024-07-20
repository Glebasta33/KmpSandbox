plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.android.library) //плагин android-библиотеки
}

kotlin {
    jvm() // 1 таргет jvm для desktop-платформы
    androidTarget() // 2 таргет для android-платформы

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                implementation(libs.koin.core)
            }
        }

        jvmMain {
            dependsOn(commonMain)
            dependencies {
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
    namespace = "com.github.kmpsandbox.featuresample"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}