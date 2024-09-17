package com.nikolic.inventory_service.event.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookConsumer {

    @KafkaListener(topics = "new-book", groupId = "inventory-group")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
