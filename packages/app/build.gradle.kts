plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.ksp).apply(false)
    kotlin("jvm")
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

kotlin {
    jvmToolchain(8)
}