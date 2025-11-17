ARG JAVA_VERSION

FROM openjdk:${JAVA_VERSION}

WORKDIR /app

RUN microdnf update && \
        microdnf install \
        nss \
        nspr \
        atk \
        libX11 \
        libxcb \
        libXcomposite \
        libXdamage \
        libXext \
        libXfixes \
        libXrandr \
        mesa-libgbm \
        ca-certificates \
        dbus-libs \
        at-spi2-atk \
        cups-libs \
        libxkbcommon \
        at-spi2-core \
        pango \
        cairo \
        alsa-lib && \
        microdnf clean all

COPY build/libs/product-processor-service-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]