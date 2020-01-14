FROM openjdk:11-jre-slim

VOLUME /tmp

RUN mkdir /app && \
    mkdir /app/log

WORKDIR /app

# Pass the path to the JAR file as an argument
# docker build . -t mondrian:latest --build-arg JAR_FILE=build/libs/mondrian-0.1-all.jar
# docker run mondrian

ARG JAR_FILE
ARG GIT_COMMIT
ARG VERSION

ENV GIT_COMMIT ${GIT_COMMIT}
ENV VERSION ${VERSION}


COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java",\
    "-Dfile.encoding=UTF8", \
     "-jar",\
     "/app/app.jar" \
     ]