FROM maven:3.8-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/product-service-0.0.1-SNAPSHOT.jar ./product-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","product-service.jar"]