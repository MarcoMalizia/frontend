FROM eclipse-temurin:17-jdk-focal

EXPOSE 8082

# Refer to Maven build -> finalName
ARG JAR_FILE=target/frontend-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /home/azureuser/myagent/_work/5/s

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} frontend-0.0.1-SNAPSHOT.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","frontend-0.0.1-SNAPSHOT.jar"]
