FROM openjdk:11-jre-slim
WORKDIR /app

COPY build/libs/service2-1.0-SNAPSHOT-microbundle.jar /app/service2-1.0-SNAPSHOT-microbundle.jar


CMD ["java", "-jar", "service2-1.0-SNAPSHOT-microbundle.jar"]