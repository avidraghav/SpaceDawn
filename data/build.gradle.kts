import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
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
//    implementation("com.google.android.material:material:1.5.0")

    // Retrofit
    implementation(libs.bundles.retrofit)
    ksp(libs.moshi.codegen)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

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

kapt {
    correctErrorTypes = true
}
