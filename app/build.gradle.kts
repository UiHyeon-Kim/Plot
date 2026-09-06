plugins {
    id("hanhyo.plot.android.application")
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.hanhyo.plot"
    defaultConfig {
        applicationId = "com.hanhyo.plot"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}
