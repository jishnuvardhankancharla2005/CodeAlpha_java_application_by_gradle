# Stage 1: Build the application with JDK 21 LTS
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copy gradle wrapper, configuration files, and source code
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src
COPY config ./config

# Fix CRLF line endings for Alpine Linux shell and set permissions
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

# Build the application using gradlew wrapper
RUN ./gradlew build -x test -x integrationTest --no-daemon

# Stage 2: Create Java 21 LTS runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy the built jar from the builder stage
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

# Change ownership to non-root user
RUN chown -R appuser:appgroup /app

USER appuser

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]
