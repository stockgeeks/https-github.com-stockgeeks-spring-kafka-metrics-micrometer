# Spring Kafka Micrometer.io example with Elasticsearch

Example project to play around with Spring Kafka Consumer metrics and Micrometer.

Versions used in this project:

* Spring Boot: 2.1.7
* Spring Kafka: 2.2.8.RELEASE
* Micrometer: 1.1.5

## How to build the project

```
./mvnw clean package
```

## How to run this project

Make sure to set the environment variable `DOCKER_HOST_IP` to the ip of the host running the 
Docker containers using docker-compose.

On Linux you can get the correct IP from docker0 interface by executing: ifconfig
In my case `DOCKER_HOST_IP=172.17.0.1`

If you are using Docker for Mac <= 1.11, or Docker Toolbox for Windows (your docker machine IP is usually 192.168.99.100)

```
docker-compose up -d
```

Start the producer:

```
cd spring-kafka-producer
mvn spring-boot:run -pl spring-kafka-producer
``` 

The producer will publish a Hello World message to topic: hello-world-messages

Start the consumer:

```
mvn spring-boot:run -pl spring-kafka-consumer
``` 

How the consumer metrics:

[http://localhost:8081/metrics/](http://localhost:8081/metrics/)

Kafka metrics are visible.

See specific Kafka metrics:

[http://localhost:8081/actuator/metrics/kafka.consumer.records.consumed.total](http://localhost:8081/actuator/metrics/kafka.consumer.records.consumed.total)

## Kafka manager 

Part of the `docker-compose` setup is the Kafka Manager.
To get more insight in the broker, topics and more you can use this tool.

* Open [http://localhost:9000](http://localhost:9000)
* Create a Kafka cluster view [http://localhost:9000/addCluster](http://localhost:9000/addCluster)

![](/documentation/images/kafka-manager.png)

## Metrics in Elasticsearch

Elasticsearch is running at: [http://localhost:9200](http://localhost:9200)

See: [IntelliJ Http client file](elasticsearch-metrics-requests.http) for all the elasticsearch queries

In case the Elasticsearch Docker container doesn't start because of error:

```
max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```

Please set the `vm_max_map_count` kernel setting needs to be set to at least 262144 to able to run Elasticsearch:

```bash
sudo sysctl -w vm.max_map_count=262144
```

The `vm_map_max_count` setting can be set permanently in `/etc/sysctl.conf`:

```bash
$ grep vm.max_map_count /etc/sysctl.conf
vm.max_map_count=262144
```
