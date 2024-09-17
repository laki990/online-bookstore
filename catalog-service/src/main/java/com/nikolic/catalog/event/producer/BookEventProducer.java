package com.nikolic.catalog.event.producer;

import com.nikolic.catalog.event.model.NewBookAddedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookEventProducer {
    private static final String TOPIC = "new-book";

    private final KafkaTemplate<String, NewBookAddedEvent> kafkaTemplate;

    public BookEventProducer(KafkaTemplate<String, NewBookAddedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNewBookEvent(NewBookAddedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println(event + " SENT");
    }
}
