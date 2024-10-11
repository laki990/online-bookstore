package com.nikolic.inventory_service.event.producer;

import com.nikolic.inventory_service.event.producer.model.BookQuantityChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookQuantityChangedProducer {
    private static final String TOPIC = "book-quantity-changed";

    private final KafkaTemplate<String, BookQuantityChangedEvent> kafkaTemplate;

    public void send(BookQuantityChangedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println(event + " SENT");
    }
}
