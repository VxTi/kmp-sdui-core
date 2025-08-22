plugins {
    `java-library`
}

group = "nl.q42"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.19.2")
}
