FROM openjdk:21-jdk

ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} service-registry.jar

ENTRYPOINT ["java", "-jar", "service-registry.jar"]
# use ENTRYPOINT command to run a command when container starts

EXPOSE 8761
