plugins {
    //apply false нужен, чтобы плагин применился только к подпроектам и gradle не требовал подключения таргета к root-проекту
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.jetbrainsCompose) apply false

}