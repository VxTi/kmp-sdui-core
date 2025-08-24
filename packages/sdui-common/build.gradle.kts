plugins {
    kotlin("multiplatform")
    kotlin("jvm") apply false
    id("com.android.library")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    api(libs.jackson.annotations)
    implementation(libs.lombok)
}

group = "nl.q42"
version = "1.0"

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()
    androidTarget {}
}

android {
    namespace = "nl.q42.sdui.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testOptions {
            targetSdk = 34
        }
        lint {
            targetSdk = 34
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
