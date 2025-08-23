group = "nl.q42"
version = "1.0"

plugins {
    id("java")
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

dependencies {

    implementation(platform(libs.bom))
    implementation(libs.dynamodb.enhanced)

    implementation(libs.spring.boot.starter.web)
    implementation(libs.jakarta.persistence.api)

    implementation(libs.jackson.databind)
    implementation(libs.gson)
    implementation(libs.commons.dbcp2)
    implementation(libs.jbcrypt)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":packages:sdui-common"))
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
