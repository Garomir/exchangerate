FROM openjdk:11-jre
ARG JAR_FILE=app.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]