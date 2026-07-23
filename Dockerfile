# Stage 1: Build the application
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Copy gradle configuration files and source code
COPY build.gradle settings.gradle ./
COPY src ./src
COPY config ./config

# Build the application (skipping tests for the docker build, as tests are run in CI pipeline)
RUN gradle build -x test -x integrationTest --no-daemon

# Stage 2: Create the minimal runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create a non-root user for better security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy the built jar from the builder stage
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

# Change ownership to the non-root user
RUN chown -R appuser:appgroup /app

USER appuser

EXPOSE 5173

ENTRYPOINT ["java", "-jar", "app.jar"]
