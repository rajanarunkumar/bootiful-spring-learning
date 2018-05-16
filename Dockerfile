FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} bootiful-learning.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=local,cassandra,amqp,webflux,h2","-jar","/bootiful-learning.jar"]
EXPOSE 61666