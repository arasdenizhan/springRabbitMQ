package com.example.springrabbitmq.service.impl;

import com.example.springrabbitmq.GetMessageRequest;
import com.example.springrabbitmq.GetMessageResponse;
import com.example.springrabbitmq.SendMessageRequest;
import com.example.springrabbitmq.SendMessageResponse;
import com.example.springrabbitmq.model.Message;
import com.example.springrabbitmq.repository.MessageRepository;
import com.example.springrabbitmq.service.MessageBrokerService;
import com.example.springrabbitmq.service.MessageService;
import com.example.springrabbitmq.service.populator.ConverterPopulator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConverterPopulator<SendMessageRequest, Message> messageConverterPopulator;
    private final ConverterPopulator<Message, SendMessageResponse> messageReverseConverterPopulator;
    private final ConverterPopulator<List<Message>, GetMessageResponse> getMessageResponseConverterPopulator;
    private final MessageBrokerService messageBrokerService;

    @Override
    public SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest) {
        SendMessageResponse response = messageReverseConverterPopulator.convert(messageRepository.save(messageConverterPopulator.convert(sendMessageRequest)));
        messageBrokerService.sendMessage(response.getMessage());
        return response;
    }

    @Override
    public GetMessageResponse getMessages(GetMessageRequest getMessageRequest) {
        return getMessageResponseConverterPopulator.convert(
                messageRepository.findAll(PageRequest.of(0, getMessageRequest.getMessageNumber(), Sort.by(Sort.Direction.DESC,"id"))).get().toList()
        );
    }
}
