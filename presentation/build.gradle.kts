plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kover)
}

android {
    namespace = "com.example.clean_mvi_compose"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.clean_mvi_compose"
        minSdk = 30
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Modules
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(libs.androidx.material3)
    testImplementation(project(":core-test"))

    //Kover Coverage (for tests) ---
    kover(project(":data"))
    kover(project(":domain"))

    // Kotlin
    implementation(libs.kotlin.stdlib.jdk7)

    // AndroidX
    implementation(libs.androidx.appcompat)
    implementation(libs.lifecycle.viewmodel.ktx)

    // Extensions
    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.extensions)

    // okHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // GSON
    implementation(libs.gson)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // --- Hilt (DI) ---
    implementation(libs.hilt.dagger.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.dagger.compiler)
    ksp(libs.hilt.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Paging
    implementation(libs.paging.compose)


    //Base
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    // Coil
    implementation(libs.coil.compose)

    // Navigation
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.datastore.preferences)

    // Material 3
    implementation(libs.androidx.compose.material.icons.extended)


    //Unit tests
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test.v173)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine.v100)
    testImplementation(libs.truth.v140)
    testImplementation(libs.robolectric)

    //Instrumented tests
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.mockwebserver)


}