ARG JAVA_VERSION
ARG API_GATEWAY_PORT_OUTER

FROM openjdk:${JAVA_VERSION}

COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE ${API_GATEWAY_PORT_INNER}

ENTRYPOINT ["java", "-jar", "app.jar"]