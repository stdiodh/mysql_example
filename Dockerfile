FROM openjdk:17-jdk

ADD /build/libs/*.jar app.jar

ENTRYPOINT ["Java", "-jar", "app.jar"]

