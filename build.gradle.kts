// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Ajoutez cette ligne pour le plugin Google Services
        // Remplacez par la dernière version si nécessaire, par exemple 4.4.2
        classpath("com.google.gms:google-services:4.4.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    // Si vous utilisez Kotlin pour Android, assurez-vous que cette ligne est présente
    // alias(libs.plugins.kotlin.android) apply false
}