FROM openjdk:11

ADD ./target/graphquery-0.0.1-SNAPSHOT.jar /usr/src/graphquery-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "graphquery-0.0.1-SNAPSHOT.jar"]