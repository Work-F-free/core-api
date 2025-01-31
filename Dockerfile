FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app
RUN mvn package -DskipTests

FROM gcr.io/distroless/java21-debian12
COPY --from=builder /app/target/*.jar /opt/app/app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java","-server","-Djava.security.egd=file:/dev/./urandom","-XX:MaxRAMPercentage=75.0","-jar","/opt/app/app.jar"]
