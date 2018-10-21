FROM anapsix/alpine-java:8

ARG PROJECT_NAME
ARG PROJECT_DESCRIPTION
ARG PROJECT_VERSION
ARG BUILD_TIMESTAMP
ARG BUILD_NUMBER
ARG JAR_FILE


# Add some labels to this image
LABEL com.mjdsoftware.app.name="${PROJECT_NAME}" \
      com.mjdsoftware.app.description="${PROJECT_DESCRIPTION}" \
      com.mjdsoftware.app.version="${PROJECT_VERSION}" \
      com.mjdsoftware.app.build.timestamp="${BUILD_TIMESTAMP}" \
      com.mjdsoftware.app.build.commit="${BUILD_NUMBER}"

VOLUME /tmp
ARG JAR_FILE

ADD ${JAR_FILE} sample-project-1.0-SNAPSHOT.jar

ENTRYPOINT java $JAVA_OPTS $JAVA_ARGS -Dspring.profiles.active=prod -jar /sample-project-1.0-SNAPSHOT.jar
