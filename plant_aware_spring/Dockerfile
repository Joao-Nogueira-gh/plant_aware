# Start with base image
FROM openjdk:8-jdk-alpine

# Add a temporary volume
VOLUME /tmp

# Expose Port 8080
EXPOSE 8080


# Application Jar File
ARG JAR_FILE=target/plant_aware-0.0.1-SNAPSHOT.jar


# Add Application Jar File to the Container
ADD ${JAR_FILE} plant_aware.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/plant_aware.jar"]
