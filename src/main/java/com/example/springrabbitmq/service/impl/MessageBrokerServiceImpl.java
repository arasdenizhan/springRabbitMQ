package com.example.springrabbitmq.service.impl;

import com.example.springrabbitmq.GetRabbitMessagesRequest;
import com.example.springrabbitmq.GetRabbitMessagesResponse;
import com.example.springrabbitmq.MessageDto;
import com.example.springrabbitmq.exception.JsonSerializeException;
import com.example.springrabbitmq.service.MessageBrokerService;
import com.example.springrabbitmq.service.populator.ConverterPopulator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageBrokerServiceImpl implements MessageBrokerService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final ConverterPopulator<Message , GetRabbitMessagesResponse> rabbitMessageReverseConverterPopulator;
    public void sendMessage(MessageDto messageDto) {
        try {
            rabbitTemplate.send("senderQueue", new Message(objectMapper.writeValueAsString(messageDto).getBytes(StandardCharsets.UTF_8)));
        } catch (JsonProcessingException e) {
            log.error("MessageBrokerServiceImpl, JSONSerializeException: {}", ExceptionUtils.getStackTrace(e));
            throw new JsonSerializeException("JSON Convert failed in sendMessage", e);
        }
    }

    @Override
    public GetRabbitMessagesResponse getMessage(GetRabbitMessagesRequest request) {
        return rabbitMessageReverseConverterPopulator.convert(rabbitTemplate.receive(request.getQueueName()));
    }
}
