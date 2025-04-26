FROM openjdk:21-jdk

ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} apigw-service.jar

ENTRYPOINT ["java", "-jar", "apigw-service.jar"]
# use ENTRYPOINT command to run a command when container starts

EXPOSE 9090
