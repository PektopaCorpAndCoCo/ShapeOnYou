plugins {
    alias(libs.plugins.android.application)
    // 1) applique le plugin Google Services
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.shapeonyou"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.shapeonyou"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    // JSON local (Gson)
    implementation("com.google.code.gson:gson:2.10.1")

    // AndroidX core
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // 2a) Firebase BoM (gère toutes les versions Firebase pour toi)
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    // 2b) Authentification
    implementation("com.google.firebase:firebase-auth")
    // (facultatif) Analytics si tu veux tracker des événements
    implementation("com.google.firebase:firebase-analytics")

    // tes autres dépendances
    // RecyclerView for list of advices
    implementation("androidx.recyclerview:recyclerview:1.3.0")

    // CardView for item layouts
    implementation("androidx.cardview:cardview:1.0.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
