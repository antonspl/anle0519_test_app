FROM openjdk:23-jdk

COPY *.jar app.jar

EXPOSE 8080

RUN chmod +x app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]