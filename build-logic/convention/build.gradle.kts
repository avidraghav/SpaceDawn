@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.build.agp)
    compileOnly(libs.build.kotlin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "spacedawn.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}
