FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/restaurant.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
