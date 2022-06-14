package com.example.springrabbitmq.service.populator;

import com.example.springrabbitmq.MessageDto;
import com.example.springrabbitmq.SendMessageResponse;
import com.example.springrabbitmq.model.Message;
import org.springframework.stereotype.Component;

@Component
public final class MessageReverseConverterPopulator extends ConverterPopulator<Message, SendMessageResponse> {
    public MessageReverseConverterPopulator() {
        super(SendMessageResponse.class);
    }

    @Override
    public void populate(Message source, SendMessageResponse target) {
        MessageDto messageDto = new MessageDto();
        messageDto.setSentDate(String.valueOf(source.getDate()));
        messageDto.setMessage(source.getData());
        target.setMessage(messageDto);
    }
}
