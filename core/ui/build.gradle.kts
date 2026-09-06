plugins {
    id("hanhyo.plot.android.library.compose")
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android { namespace = "com.hanhyo.plot.core.ui" }

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.bundles.compose.ui)
    api(libs.androidx.compose.material.icons.extended)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
