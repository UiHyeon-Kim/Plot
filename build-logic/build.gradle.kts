plugins {
    `kotlin-dsl`
}

group = "com.hanhyo.plot.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)  // JvmLibraryConventionPlugin에서 사용
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "hanhyo.plot.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "hanhyo.plot.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "hanhyo.plot.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("jvmLibrary") {
            id = "hanhyo.plot.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "hanhyo.plot.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidFeature") {
            id = "hanhyo.plot.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}
