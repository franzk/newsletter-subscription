FROM gradle:7.6.2-jdk17 AS build
WORKDIR /app

COPY --chown=gradle:gradle . .
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]
