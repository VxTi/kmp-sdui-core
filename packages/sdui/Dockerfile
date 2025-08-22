# Use a base image with JDK
FROM openjdk:17-jdk-slim AS build

# Set working directory
WORKDIR /app

# Copy the build files (if using Gradle or Maven)
COPY build.gradle.kts ./
COPY settings.gradle.kts ./
COPY src ./src

# Install dependencies and build the application
RUN ./gradlew build --no-daemon

# Use a smaller base image for the final stage
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]