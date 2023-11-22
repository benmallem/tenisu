FROM maven:latest AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/tenisu-0.0.1-SNAPSHOT.jar tenisu.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","tenisu.jar"]


