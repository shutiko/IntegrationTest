FROM openjdk:17-jdk-slim-buster
EXPOSE 8081
ADD target/Conditional-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]