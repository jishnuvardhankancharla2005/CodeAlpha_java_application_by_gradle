# Create Java 21 LTS runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy the prebuilt jar from the host build directory
COPY build/libs/*-SNAPSHOT.jar app.jar

# Change ownership to non-root user
RUN chown -R appuser:appgroup /app

USER appuser

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]
