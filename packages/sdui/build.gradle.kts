plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "nl.q42"
version = "1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

dependencies {

    implementation(platform("software.amazon.awssdk:bom:2.32.29"))
    implementation("software.amazon.awssdk:dynamodb-enhanced")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("org.apache.commons:commons-dbcp2:2.12.0")
    implementation("org.mindrot:jbcrypt:0.4")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    implementation(project(":sdui-common"))
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