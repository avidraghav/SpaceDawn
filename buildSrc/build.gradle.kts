plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("HiltPlugin") {
            id = "com.raghav.hiltplugin"
            implementationClass = "HiltPlugin"
            version = "1.0.0"
        }
    }
}
