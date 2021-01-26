FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} auction-platform.jar
ENTRYPOINT ["java","-jar","/auction-platform.jar"]
EXPOSE 8082