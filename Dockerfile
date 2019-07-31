FROM openjdk:8-jdk-alpine

VOLUME /tmp
COPY public/* public/
COPY target/api-orders-*.jar api-orders.jar
ENTRYPOINT ["java", "-jar", "api-orders.jar", "-Djava.security.egd=file:/dev/./urandom", "-cp", ".", "-server", "-Dserver.port=8080", "com.navent.api.orders.App"]
