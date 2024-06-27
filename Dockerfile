FROM openjdk:17-bullseye

ADD build/libs/gzac-backend-template.war /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]