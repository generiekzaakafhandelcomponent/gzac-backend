FROM eclipse-temurin:17-jre-noble

ADD build/libs/gzac-backend-template.war /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]