package com.example.springrabbitmq.service.populator;

import com.example.springrabbitmq.GetMessageResponse;
import com.example.springrabbitmq.MessageDto;
import com.example.springrabbitmq.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class GetMessageResponseConverterPopulator extends ConverterPopulator<List<Message>, GetMessageResponse>{
    public GetMessageResponseConverterPopulator() {
        super(GetMessageResponse.class);
    }

    @Override
    public void populate(List<Message> source, GetMessageResponse target) {
        source.forEach(message -> {
            MessageDto messageDto = new MessageDto();
            messageDto.setSentDate(String.valueOf(message.getDate()));
            messageDto.setMessage(message.getData());
            target.getMessage().add(messageDto);
        });
    }
}
