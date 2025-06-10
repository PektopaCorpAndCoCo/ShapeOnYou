plugins {
    // Déclaration des plugins et de leurs versions (appliqués dans les modules)
    id("com.android.application")       version "8.10.1" apply false
    id("com.android.library")           version "8.10.1" apply false
    id("org.jetbrains.kotlin.android")  version "1.8.22" apply false
    id("com.google.gms.google-services")version "4.4.0" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
