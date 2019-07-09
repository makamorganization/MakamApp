FROM openjdk:11
ADD target/MakamApp.jar MakamApp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "MakamApp.jar"]
