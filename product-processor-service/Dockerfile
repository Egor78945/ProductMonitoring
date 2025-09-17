ARG JAVA_VERSION

FROM openjdk:${JAVA_VERSION}-jdk-slim

WORKDIR /app

RUN apt-get update && \
    apt-get install -y \
    libnss3 \
    libnspr4 \
    libatk1.0-0 \
    libx11-6 \
    libxcb1 \
    libxcomposite1 \
    libxdamage1 \
    libxext6 \
    libxfixes3 \
    libxrandr2 \
    libgbm1 \
    ca-certificates \
    libdbus-1-3 \
    libatk-bridge2.0-0 \
    libcups2 \
    libxkbcommon0 \
    libatspi2.0-0 \
    libpango-1.0-0 \
    libcairo2 \
    libasound2 \
    && rm -rf /var/lib/apt/lists/*

COPY build/libs/product-processor-service-0.0.1-SNAPSHOT.jar /app/app.jar
COPY build/generated-src/jooq /app/generated-src/jooq

ENTRYPOINT ["java", "-jar", "app.jar"]