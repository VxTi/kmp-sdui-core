# Dockerfile
# Multi-stage build for a Spring Boot Gradle project

# 1) Build stage
FROM gradle:8.5-jdk21 AS build
WORKDIR /workspace

# Copy Gradle wrapper and build files first to leverage Docker layer cache
COPY gradlew .
COPY gradle ./gradle
COPY settings.gradle.kts ./
COPY settings.gradle.kts ./

# Pre-fetch dependencies (optional but speeds up subsequent builds)
# The specific command might vary based on your project structure and plugins
# This is a common way to download dependencies:
RUN ./gradlew dependencies --info

# Now copy sources and build
COPY src ./src
RUN ./gradlew build -x test # Build the project, skipping tests

# 2) Runtime stage
FROM openjdk:23-jdk
WORKDIR /app

# Copy the built JAR from the builder stage and give it a stable name
# Adjust the path to the JAR file based on your Gradle build output
# Typically, it's in build/libs/your-project-name.jar
COPY --from=build /workspace/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=docker
ENV SERVER_PORT=8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
