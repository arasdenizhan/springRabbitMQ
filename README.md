# Spring RabbitMQ Example

- Spring Boot, H2 Database, RabbitMQ, SOAP
- Back-end serves at `http://localhost:8080`.
- RabbitMQ serves at `http://localhost:5672`.
- RabbitMQ Management Panel serves at `http://localhost:15672`.
- SOAP Service WSDL available at `http://localhost:8080/ws/RabbitMq.wsdl`.
- To run RabbitMQ on local:
    - Find `Makefile` file which is located under the root folder of project and run goal with name `rabbit_mq`.

## Sample Requests

### Test Rabbit Message Request
- Takes a String input from the user and send it to a queue which is in RabbitMQ. A listener method will log the consumed test message to the console if the test is successful.
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rab="http://arasdenizhan.github.io/rabbit">
   <soapenv:Header/>
   <soapenv:Body>
      <rab:testRabbitMqRequest>
         <rab:testMessage>Test Message</rab:testMessage>
      </rab:testRabbitMqRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
- Result Log :
```
INFO 11653 --- [ntContainer#0-1] c.e.s.s.impl.MessageBrokerServiceImpl    : Consumed test message: {"message":"Test Message","sentDate":"Tue Jun 14 11:52:13 TRT 2022"}
```


### Send Message Request
- Takes a String input from the user builds Message entity, saves this entity to H2 Database and send this message to a predefined Queue called "senderQueue".
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rab="http://arasdenizhan.github.io/rabbit">
   <soapenv:Header/>
   <soapenv:Body>
      <rab:sendMessageRequest>
         <rab:message>Denizhan, test2</rab:message>
      </rab:sendMessageRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### Get Message Request
- Takes an integer input from the user and gets last records according to this input from the H2 Database.
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rab="http://arasdenizhan.github.io/rabbit">
   <soapenv:Header/>
   <soapenv:Body>
      <rab:getMessageRequest>
         <rab:messageNumber>5</rab:messageNumber>
      </rab:getMessageRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### Get Rabbit Message Request
- Takes a String input from the user and consumes a message from the queue which is in RabbitMQ. Queue name is the taken String input.
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rab="http://arasdenizhan.github.io/rabbit">
   <soapenv:Header/>
   <soapenv:Body>
      <rab:getRabbitMessagesRequest>
         <rab:queueName>senderQueue</rab:queueName>
      </rab:getRabbitMessagesRequest>
   </soapenv:Body>
</soapenv:Envelope>
```


