import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<ApplicationExtension> {
                compileSdk = libs.sdkLevel("compileSdk")
                defaultConfig {
                    minSdk = libs.sdkLevel("minSdk")
                    targetSdk = libs.sdkLevel("targetSdk")
                }
                target.configureJavaKotlin(this)
                target.configureDetekt()
                buildFeatures {
                    compose = true
                }
            }
        }
    }
}
