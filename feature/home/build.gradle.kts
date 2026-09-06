plugins {
    id("hanhyo.plot.android.feature")
}

android { namespace = "com.hanhyo.plot.feature.home" }

dependencies {
    implementation(project(":core:domain"))

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
