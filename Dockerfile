FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/MessageStreamingApp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","--add-exports", "java.base/sun.nio.ch=ALL-UNNAMED","-jar","/app.jar"]