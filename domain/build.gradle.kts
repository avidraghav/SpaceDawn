import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("com.google.devtools.ksp")
    id("org.jlleitschuh.gradle.ktlint")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
    implementation(libs.androidx.annotations)
    implementation(libs.java.inject)

    // Room
    implementation(libs.room.common)

    // Moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // Coroutines Flow
    implementation(libs.coroutines.core)

    implementation(libs.okhttp.interceptor)

    api(libs.kotlin.immutable.collections)
}
