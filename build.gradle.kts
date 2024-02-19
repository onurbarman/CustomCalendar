buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}