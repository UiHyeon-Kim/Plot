plugins {
    id("hanhyo.plot.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
}

android { namespace = "com.hanhyo.plot.core.data" }

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    // Room — uncomment when needed
    // implementation(libs.androidx.room.runtime)
    // implementation(libs.androidx.room.ktx)
    // ksp(libs.androidx.room.compiler)
    // DataStore — uncomment when needed
    // implementation(libs.androidx.datastore.preferences)
}
