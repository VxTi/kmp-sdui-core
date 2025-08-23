plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.application)
}

group = "nl.q42"
version = "1.0"


android {
    namespace = "nl.q42"
    compileSdk = 36

    defaultConfig {
        minSdk = 21
        //noinspection OldTargetApi
        targetSdk = 35
        applicationId = "nl.q42.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
}


kotlin {
    androidTarget()
    jvm()


    sourceSets {
        val commonMain by getting {
            dependencies {
                // Dependencies common to all targets
                // If jackson-annotations are used in common code, declare them here
                // as an api or implementation dependency.
                // Use "api" if the annotations are part of the public API of your library.
                api(libs.jackson.annotations)
            }
        }
    }

    jvmToolchain(23)
}