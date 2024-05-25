@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    id("spacedawn.android.hilt")
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.raghav.spacedawnv2"
    compileSdk = libs.versions.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "com.raghav.spacedawnv2"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

ktlint {
    android.set(true)
    ignoreFailures.set(false)
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
    }
    filter {
        exclude {
            it.file.path.contains("$buildDir/generated/")
        }
    }
    disabledRules.set(listOf("max-line-length"))
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.androidx.corektx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.navigation)

    // Coil
    implementation(libs.coil)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // WorkManager
    implementation(libs.workmanager)
    implementation(libs.hilt.work)
    kapt(libs.hilt.compiler)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Navigation with Hilt and Compose
    implementation(libs.hilt.navigation)

    // Splash Screen
    implementation(libs.splashscreen.core)

    // Test Dependencies
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.androidx.test.extensions)
    androidTestImplementation(libs.androidx.expresso.core)

    testImplementation(libs.coroutines.test)

    // For making Assertions in Test cases
    testImplementation(libs.google.truth)
    androidTestImplementation(libs.google.truth)
}
