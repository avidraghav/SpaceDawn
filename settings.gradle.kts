pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

/* https://github.com/gradle/gradle/issues/28407 */
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "SpaceDawn"
include(":app")
include(":domain")
include(":data")
