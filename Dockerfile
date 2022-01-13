FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/bankend.jar bankend.jar
ENTRYPOINT ["java","-jar","/bankend.jar"]