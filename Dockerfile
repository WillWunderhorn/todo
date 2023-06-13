FROM maven:3.8.4-openjdk-11 AS build
COPY .mvn /home/app/src
WORKDIR /home/app/src
RUN mvn package

FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/app/src/target/*.jar /app/todoApp.jar

ENTRYPOINT ["java", "-jar", "/app/todoApp.jar"]