FROM maven:3.5-jdk-8-slim

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline de.qaware.maven:go-offline-maven-plugin:resolve-dependencies -B
