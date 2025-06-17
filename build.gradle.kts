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
    // Déclaration des plugins et de leurs versions (appliqués dans les modules)
    alias(libs.plugins.android.application) apply false
    id("com.android.library")           version "8.10.1" apply false
    id("org.jetbrains.kotlin.android")  version "1.8.22" apply false
    id("com.google.gms.google-services")version "4.4.0" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
