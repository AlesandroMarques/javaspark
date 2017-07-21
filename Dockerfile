FROM maven:3-alpine
RUN mkdir -p /usr/src/spark
COPY . /usr/src/spark
WORKDIR /usr/src/spark

RUN mvn clean package

ENTRYPOINT java -jar ./target/spark-1.0-SNAPSHOT-jar-with-dependencies.jar