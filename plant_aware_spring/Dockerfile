FROM ubuntu
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY install_rmq.sh .
RUN chmod u+x install_rmq.sh
RUN apt-get update && apt-get install sudo
RUN ./install_rmq.sh
RUN apt-get install openjdk-8-jdk
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
