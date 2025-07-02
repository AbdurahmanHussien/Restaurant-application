FROM openjdk:21

WORKDIR /app

COPY target/spring-restaurant.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
