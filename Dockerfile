FROM ubuntu:latest
RUN apt-get update
RUN apt-get apt-utils
RUN apt-get install -y openjdk-8-jdk wget maven
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927
RUN echo "deb http://repo.mongodb.org/apt/ubuntu $(cat /etc/lsb-release | grep DISTIB_CODENAME | cut -d= -f2) /mongodb-org/3.2"
RUN apt-get update && apt-get install -y mongodb-org
RUN mkdir -p /data/db
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
RUN wget http://apache.rediris.es/kafka/1.1.0/kafka_2.11-1.1.0.tgz
RUN tar -xzf kafka_2.11-1.1.0.tgz
ADD ./ ./project/
ADD ./run.sh .
RUN chmod +x run.sh
EXPOSE 8080
EXPOSE 8085
EXPOSE 8090 
EXPOSE 27017
ENTRYPOINT ["/usr/bin/mongodb"]
RUN cd /project && mvn package -DskipTests
CMD ./run.sh
