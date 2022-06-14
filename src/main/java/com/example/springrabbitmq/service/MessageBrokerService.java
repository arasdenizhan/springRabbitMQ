package com.example.springrabbitmq.service;

import com.example.springrabbitmq.*;

public interface MessageBrokerService {
    void sendMessage(MessageDto messageDto);
    TestRabbitMqResponse testMessage(TestRabbitMqRequest request);
    GetRabbitMessagesResponse getMessage(GetRabbitMessagesRequest request);
}
