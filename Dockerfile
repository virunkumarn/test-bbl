FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/demo-application.jar app/app.jar
COPY build/libs/lib/* app/lib/
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]