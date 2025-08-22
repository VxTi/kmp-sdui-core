# Dockerfile
# Multi-stage build for a Spring Boot Maven project

# 1) Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy only the Maven descriptor first to leverage Docker layer cache
COPY pom.xml ./
# Pre-fetch dependencies (optional but speeds up subsequent builds)
RUN mvn -q -DskipTests dependency:go-offline

# Now copy sources and build
COPY src ./src
RUN mvn -DskipTests package

# 2) Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built JAR from the builder stage and give it a stable name
COPY --from=build /workspace/target/*.jar /app/app.jar

# Optional: if you rely on a healthcheck that uses wget (present in alpine)
# you can keep it; otherwise you can switch to curl by installing it:
# RUN apk add --no-cache curl

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=docker
ENV SERVER_PORT=8080
ENTRYPOINT ["java","-jar","/app/app.jar"]