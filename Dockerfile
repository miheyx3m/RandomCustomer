FROM openjdk:8-jdk-alpine

LABEL maintainer="mikhail_zotov"

COPY target/random-customers*.jar /random-customers.jar

CMD ["java","-jar","/random-customers.jar"]