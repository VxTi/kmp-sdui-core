
plugins {
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"

    id("com.android.application") apply false
    kotlin("multiplatform") apply false
    kotlin("jvm")

    kotlin("plugin.lombok") version "2.2.10"
    id("io.freefair.lombok") version "8.14.2"

}

group = "nl.q42"
version = "1.0"

dependencies {

    implementation(platform(libs.bom))
    implementation(libs.dynamodb.enhanced)

    implementation(libs.spring.boot.starter.web)
    implementation(libs.jakarta.persistence.api)

    implementation(libs.jackson.databind)
    implementation(libs.gson)
    implementation(libs.commons.dbcp2)
    implementation(libs.jbcrypt)

    implementation(libs.kotlinx.serialization.json)

    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":sdui-common")) {
        attributes {
            attribute(
                org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.attribute,
                org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.jvm
            )
        }
        capabilities {
            requireCapability("nl.q42:sdui-common")
        }
    }
}

kotlin {
    jvmToolchain(23)
}

sourceSets {
    named("main") {
        resources {
            srcDir("src/main/java")
            include("**/*.json")
            srcDir("src/main/resources")
        }
    }
}

springBoot {
    mainClass.set("nl.q42.Main")
}
