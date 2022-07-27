#Build Stage

FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#Package Stage
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/bankend.jar bankend.jar
ENTRYPOINT ["java","-jar","/bankend.jar"]