import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("hanhyo.plot.android.library.compose")
                apply("hanhyo.plot.android.hilt")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                // Compose UI는 :core:ui가 api로 재노출한다 — 여기서 다시 추가하지 않는다.
                add("implementation", project(":core:ui"))
                add("implementation", libs.findLibrary("androidx-navigation-compose").get())
                add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())
                add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
            }
        }
    }
}
