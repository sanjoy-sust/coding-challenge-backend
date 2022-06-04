FROM adoptopenjdk/openjdk11:latest
COPY /target/app-0.0.1-SNAPSHOT.jar app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","app-0.0.1-SNAPSHOT.jar"]