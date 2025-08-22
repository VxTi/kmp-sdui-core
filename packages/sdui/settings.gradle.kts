plugins {
    id 'java'
    id 'org.springframework.boot' version '3.4.1'
    id 'io.spring.dependency-management' version '1.1.0' // Or latest
}

group = 'nl.q42'
version = '1.0-SNAPSHOT'

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
}

repositories {
    mavenCentral()
    // For Spring milestone versions like 4.0.0-M1, 4.0.0-M4
    // You might need to add Spring's milestone repository if not found in mavenCentral
    maven { url 'https://repo.spring.io/milestone' }
    maven { url 'https://repo.spring.io/snapshot' } // For SNAPSHOT versions if any
}

dependencies {
    implementation 'software.amazon.awssdk:dynamodb'

    implementation 'org.springframework.boot:spring-boot-starter-web:4.0.0-M1' // Check Spring Boot docs for Java 23 compatibility with this milestone

    implementation 'org.postgresql:postgresql:42.7.4'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa:4.0.0-M1'
    implementation 'org.springframework.boot:spring-boot-starter-jdbc:4.0.0-M1'
    implementation 'org.springframework.boot:spring-boot-autoconfigure:4.0.0-M1'
    implementation 'org.hibernate.orm:hibernate-core:7.0.0.Beta3'
    implementation 'jakarta.persistence:jakarta.persistence-api:3.2.0'
    implementation 'org.springframework.data:spring-data-jpa:4.0.0-M4'

    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
    implementation 'com.google.code.gson:gson:2.11.0'
    implementation 'org.apache.commons:commons-dbcp2:2.12.0'
    implementation 'org.mindrot:jbcrypt:0.4'

    compileOnly 'org.projectlombok:lombok:1.18.36'
    annotationProcessor 'org.projectlombok:lombok:1.18.36' // Consistent with your maven-compiler-plugin

    implementation 'nl.q42:sdui-common:1.0-SNAPSHOT' // Assuming this is another local project or published artifact

    testImplementation 'junit:junit:4.13.2'
}

// Configuration for including .json files from src/main/java in resources
sourceSets {
    main {
        resources {
            srcDir 'src/main/java'
            include '**/*.json'
        }
        // If you also have resources in src/main/resources (the default location)
        // ensure it's also included or Gradle will only consider the one you defined above.
        // resources { srcDir 'src/main/resources' } // This is usually default
    }
}

// Spring Boot repackage goal is handled by the Spring Boot Gradle plugin's bootJar task
// bootJar {
//    mainClass = 'your.main.ApplicationClass' // Specify your main class if not auto-detected
// }

// The maven-compiler-plugin configuration for annotation processors is handled
// by adding lombok to the annotationProcessor configuration in dependencies.
// The source and target compatibility are set in the java block.
