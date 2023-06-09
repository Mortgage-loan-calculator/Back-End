FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the Gradle files
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Download and install Gradle
RUN ./gradlew --version

# Copy the project files
COPY . /app

# Build the project
RUN ./gradlew build -x test

# Set the startup command
CMD ["java", "-jar", "build/libs/mortgage.calculator-0.0.1-SNAPSHOT.jar"]
