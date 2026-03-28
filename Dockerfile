# Stage 1: Build the JARs
FROM gradle:8-jdk17 AS build
WORKDIR /home/gradle/project

# Copy Gradle wrapper and configuration files first (for better caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle gradle.properties ./

# Copy the entire project source (respects .dockerignore)
COPY . .

# Provide the service name as an argument to decide what to build
ARG SERVICE_NAME
RUN ./gradlew :${SERVICE_NAME}:bootJar -x test

# Stage 2: Runtime image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# The service name is needed again to find the resulting JAR
ARG SERVICE_NAME
COPY --from=build /home/gradle/project/${SERVICE_NAME}/build/libs/*.jar app.jar



EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
