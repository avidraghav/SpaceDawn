
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            pluginManager.apply(libs.findPlugin("hilt").get().get().pluginId)
            pluginManager.apply(libs.findPlugin("kapt").get().get().pluginId)

            dependencies {
                "implementation"(libs.findLibrary("hilt.android").get())
                "kapt"(libs.findLibrary("hilt.android.compiler").get())
            }
            kapt().apply {
                correctErrorTypes = true
            }
        }
    }

    private fun Project.kapt(): KaptExtension {
        return extensions.getByType(KaptExtension::class.java)
    }
}
