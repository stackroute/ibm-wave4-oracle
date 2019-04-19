FROM openjdk:11
ADD ./target/timeseries-conversation-service-0.0.1-SNAPSHOT.jar /usr/src/timeseries-conversation-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "timeseries-conversation-service-0.0.1-SNAPSHOT.jar"]