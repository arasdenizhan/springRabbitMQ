package com.example.springrabbitmq.service;

import com.example.springrabbitmq.GetRabbitMessagesRequest;
import com.example.springrabbitmq.GetRabbitMessagesResponse;
import com.example.springrabbitmq.MessageDto;

public interface MessageBrokerService {
    void sendMessage(MessageDto messageDto);
    GetRabbitMessagesResponse getMessage(GetRabbitMessagesRequest request);
}
