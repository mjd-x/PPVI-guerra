FROM openjdk:11
ADD target/guerra.jar guerra.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "guerra.jar"]