package com.example.springrabbitmq.service.populator;

import com.example.springrabbitmq.SendMessageRequest;
import com.example.springrabbitmq.model.Message;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public final class MessageConverterPopulator extends ConverterPopulator<SendMessageRequest, Message> {
    public MessageConverterPopulator() {
        super(Message.class);
    }

    @Override
    public void populate(SendMessageRequest source, Message target) {
        target.setDate(new Date());
        target.setData(source.getMessage());
    }
}
