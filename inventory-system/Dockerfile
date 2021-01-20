FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} inventory-system.jar
ENTRYPOINT ["java","-jar","/inventory-system.jar"]
EXPOSE 8081