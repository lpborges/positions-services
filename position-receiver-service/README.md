# Position Receiver Service

REST Service responsible for receive positions and post it on a Kafka topic using Kotling with Spting Cloud Stream (SCTs)

It's need a Kafka and Zookper server running, the host and ports can be changed at the application.yaml (see: http://docs.spring.io/spring-cloud-stream/docs/Brooklyn.SR1/reference/html/_apache_kafka_binder.html#_kafka_binder_properties for SCTs properties)

For read the messages using a no SCTs consumer, set the property ```spring.cloud.stream.bindings.output.producer.headerMode=raw``` to not send headers embeded with the payload.

This services send messages for Kafka in async mode, so no errors are sent to client (can be changed settting the property ```spring.cloud.stream.kafka.bindings.output.producer.sync=true```). The async errors are handle by ```br.com.lpborges.position.receiver.integration.ErrorHandler#handle``` that just log the error (feel free to change the class to a better error handle, like send the JSON to a file ou reschedule the message).

To send a position to test:
```
curl -X POST -H "Content-Type: application/json" -d ' {
          "idTracker": 12345,
          "position": {
              "time": 123456,
              "coordinate": {
                "longitude": 5.15,
                "latitude": 3.82
              }
          }
        }' "http://localhost:8080/api/positions"
```
