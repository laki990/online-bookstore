package com.nikolic.catalog.event.producer;

import com.nikolic.catalog.event.model.NewBookAddedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookEventProducer {
    private static final String TOPIC = "new-book";

    private final KafkaTemplate<String, NewBookAddedEvent> kafkaTemplate;

    public void send(NewBookAddedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println(event + " SENT");
    }
}
