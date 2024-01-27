# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/notification-service.jar /app/notification-service.jar

# Expose the port that your Spring Boot application runs on
EXPOSE 8082

# Specify the command to run your application
CMD ["java", "-jar", "notification-service.jar"]
