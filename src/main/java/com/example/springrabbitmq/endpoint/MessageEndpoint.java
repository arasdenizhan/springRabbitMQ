package com.example.springrabbitmq.endpoint;

import com.example.springrabbitmq.*;
import com.example.springrabbitmq.service.MessageBrokerService;
import com.example.springrabbitmq.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class MessageEndpoint {
    private static final String NAMESPACE_URI = "http://arasdenizhan.github.io/rabbit";

    private final MessageService messageService;
    private final MessageBrokerService messageBrokerService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageRequest")
    @ResponsePayload
    public GetMessageResponse getMessages(@RequestPayload GetMessageRequest request) {
        return messageService.getMessages(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRabbitMessagesRequest")
    @ResponsePayload
    public GetRabbitMessagesResponse getRabbitMessages(@RequestPayload GetRabbitMessagesRequest request) {
        return messageBrokerService.getMessage(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendMessageRequest")
    @ResponsePayload
    public SendMessageResponse sendMessage(@RequestPayload SendMessageRequest request){
        return messageService.sendMessage(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "testRabbitMqRequest")
    @ResponsePayload
    public TestRabbitMqResponse testRabbitMq(@RequestPayload TestRabbitMqRequest request){
        return messageBrokerService.testMessage(request);
    }
}
