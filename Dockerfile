FROM eclipse-temurin:17-jdk-focal
EXPOSE 8082
ARG JAR_FILE=target/frontend-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} frontend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","frontend-0.0.1-SNAPSHOT.jar"]
