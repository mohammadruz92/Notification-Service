FROM registry.access.redhat.com/ubi9/openjdk-17:1.13-12

ARG BUILD_DATE
ARG BUILD_VERSION

LABEL maintainer="NotificationDefault"
LABEL org.label-schema.build-date=$BUILD_DATE
LABEL org.label-schema.version=$BUILD_VERSION
ARG JAR_FILE=target/notification.service-0.0.1-SNAPSHOT-exec.jar

WORKDIR /opt/app

COPY ${JAR_FILE} application.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","application.jar","--server.port=80"]