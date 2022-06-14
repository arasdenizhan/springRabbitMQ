package com.example.springrabbitmq.service.populator;

import com.example.springrabbitmq.MessageDto;
import com.example.springrabbitmq.TestRabbitMqRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestMessageConverterPopulator extends ConverterPopulator<TestRabbitMqRequest, MessageDto> {

    public TestMessageConverterPopulator() {
        super(MessageDto.class);
    }


    @Override
    public void populate(TestRabbitMqRequest source, MessageDto target) {
        target.setMessage(source.getTestMessage());
        target.setSentDate(String.valueOf(new Date()));
    }
}
