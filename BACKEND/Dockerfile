FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/idgs09_01-0.0.1-SNAPSHOT.jar /app/ms-pe.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ms-pe.jar"]