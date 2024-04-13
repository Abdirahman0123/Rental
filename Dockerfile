FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
COPY target/Rental-0.0.1-SNAPSHOT.jar Rental.jar
ENTRYPOINT [ "java", "-jar", "Rental.jar" ]
