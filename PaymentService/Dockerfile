FROM openjdk:21-jdk

ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} payment-service.jar

ENTRYPOINT ["java", "-jar", "payment-service.jar"]
# use ENTRYPOINT command to run a command when container starts

EXPOSE 8081
