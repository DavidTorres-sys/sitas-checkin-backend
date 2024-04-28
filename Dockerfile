# Use a base image with Java and Maven pre-installed
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project definition
COPY pom.xml .

# Resolve dependencies and cache them
RUN mvn dependency:go-offline

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Create a new stage for running the application
FROM openjdk:17-slim AS runtime

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=build /app/target/checkin-0.0.1-SNAPSHOT.jar .

# Expose the port your application runs on
EXPOSE 8080

# Delay the start of the application by 60 seconds
CMD ["sh", "-c", "sleep 1 && java -jar checkin-0.0.1-SNAPSHOT.jar"]
