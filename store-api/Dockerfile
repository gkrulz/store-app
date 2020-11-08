FROM openjdk:8-jdk-alpine3.9
WORKDIR /app

COPY . .
RUN ./gradlew build -x test
WORKDIR /app/build
EXPOSE 8080
CMD ["java", "-jar", "libs/store-api-0.0.1-SNAPSHOT.jar"]

