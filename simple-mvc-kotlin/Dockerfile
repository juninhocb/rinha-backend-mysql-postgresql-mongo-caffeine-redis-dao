FROM openjdk:17

WORKDIR /app

COPY target/simple-mvc-kotlin-0.0.1-SNAPSHOT.jar /app/simple-mvc-kotlin.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "simple-mvc-kotlin.jar"]