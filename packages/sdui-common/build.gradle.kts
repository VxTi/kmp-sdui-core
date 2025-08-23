plugins {
    id("java-library")
}

group = "nl.q42"
version = "1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

dependencies {
    // Jackson annotations for DTOs / shared models
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.19.2")
}