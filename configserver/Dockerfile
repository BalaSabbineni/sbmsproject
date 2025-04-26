FROM openjdk:21-jdk

ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]
# use ENTRYPOINT command to run a command when container starts

EXPOSE 9296
