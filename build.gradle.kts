plugins {
    //apply false нужен, чтобы плагин применился только к подпроектам и gradle не требовал подключения таргета к root-проекту
    //Плагины для KMP и CMP
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    //Плагины для Android:
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization)
}