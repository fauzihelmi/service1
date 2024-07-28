FROM openjdk:24-slim-bullseye
LABEL maintainer="fauzihelmi39@gmail.com"
WORKDIR /app
COPY target/service-transaction-0.0.1-SNAPSHOT.war app.war
EXPOSE 8095
ENTRYPOINT ["java", "-jar", "app.war"]