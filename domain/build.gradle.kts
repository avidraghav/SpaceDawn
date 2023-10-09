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
    android.set(false)
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
}

dependencies {
    implementation("androidx.annotation:annotation:1.6.0")
    implementation("javax.inject:javax.inject:1")

    // Room
    implementation("androidx.room:room-common:2.5.1")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    // Coroutines Flow
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")

    val okhttp_interceptor = "5.0.0-alpha.2"
    api("com.squareup.okhttp3:logging-interceptor:$okhttp_interceptor")

    // For Making Kotlin Collections Immutable as to be considered stable
    api("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
}
