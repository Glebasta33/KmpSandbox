plugins {
    alias(libs.plugins.android.application) //плагин android-приложения
    alias(libs.plugins.kotlin.android) //кotlin для android
}

android {
    namespace = "com.github.kmpsandbox.android"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        applicationId = "com.github.kmpsandbox"
        versionCode = 1
        versionName = "0.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    dependencies {
        implementation(libs.androidx.activity.compose) // активити нужна только внутри таргета, поэтому подключается здесь
        implementation(project(":common")) // подключаем common-код
    }
}