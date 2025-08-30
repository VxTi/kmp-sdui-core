plugins {
    kotlin("multiplatform") apply false

    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.ksp).apply(false)
    kotlin("jvm")
}
dependencies {
    implementation(kotlin("stdlib-jdk17"))
}

kotlin {
    jvmToolchain(17)
}