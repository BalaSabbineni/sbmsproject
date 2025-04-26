FROM openjdk:21-jdk

ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} order-service.jar

ENTRYPOINT ["java", "-jar", "order-service.jar"]
# use ENTRYPOINT command to run a command when container starts

EXPOSE 8082
