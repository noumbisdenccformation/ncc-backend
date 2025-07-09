FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/ncc-backend-1.0.0.jar"]