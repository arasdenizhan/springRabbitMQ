package com.example.springrabbitmq.service.populator;

import com.example.springrabbitmq.GetRabbitMessagesResponse;
import com.example.springrabbitmq.MessageDto;
import com.example.springrabbitmq.exception.JsonSerializeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public final class RabbitMessageReverseConverterPopulator extends ConverterPopulator<Message, GetRabbitMessagesResponse> {
    private final ObjectMapper objectMapper;

    public RabbitMessageReverseConverterPopulator() {
        super(GetRabbitMessagesResponse.class);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void populate(Message source, GetRabbitMessagesResponse target) {
        try {
            target.getMessage().add(objectMapper.readValue(source.getBody(), MessageDto.class));
        } catch (NullPointerException | IOException e) {
            log.error("RabbitMessageReverseConverterPopulator, JSONSerializeException: {}", ExceptionUtils.getStackTrace(e));
            throw new JsonSerializeException("JSON Convert failed in populate", e);
        }
    }
}
