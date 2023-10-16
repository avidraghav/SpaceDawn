@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    id("spacedawn.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.raghav.data.spacedawnv2"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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

    implementation(libs.androidx.corektx)
    implementation(libs.androidx.appcompat)

    // Retrofit
    implementation(libs.bundles.retrofit)
    ksp(libs.moshi.codegen)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.androidx.test.extensions)
    androidTestImplementation(libs.androidx.expresso.core)

    testImplementation(libs.coroutines.test)

    // For making Assertions in Test cases
    testImplementation(libs.google.truth)
    androidTestImplementation(libs.google.truth)

    // Mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    androidTestImplementation(libs.androidx.test.core)
}
