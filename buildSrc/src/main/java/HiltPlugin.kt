import org.gradle.api.Plugin
import org.gradle.api.Project

class HiltPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.google.dagger.hilt.android")
        target.plugins.apply("kotlin-kapt")
        target.dependencies.add("implementation", "com.google.dagger:hilt-android:2.47")
        target.dependencies.add("kapt", "com.google.dagger:hilt-compiler:2.47")
    }
}
