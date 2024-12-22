# Use a base image with Java runtime
FROM openjdk:21-jdk-slim

# Add a label with your application's metadata
LABEL maintainer="pospravah.dev@gmail.com"

# Set the working directory
WORKDIR /app

# Remove any existing files in the /app directory
RUN rm -rf /app/demo-image-manage-api.jar

# Copy the JAR file into the container
COPY build/libs/demo-image-manage-api.jar /app/demo-image-manage-api.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/demo-image-manage-api.jar"]
