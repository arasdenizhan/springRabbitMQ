package com.example.springrabbitmq.service;

import com.example.springrabbitmq.GetMessageRequest;
import com.example.springrabbitmq.GetMessageResponse;
import com.example.springrabbitmq.SendMessageRequest;
import com.example.springrabbitmq.SendMessageResponse;

public interface MessageService {
    SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest);
    GetMessageResponse getMessages(GetMessageRequest getMessageRequest);
}
