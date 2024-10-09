package com.nikolic.order.event.producer;

import com.nikolic.order.event.model.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    private static final String TOPIC = "new-order-created";

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNewOrderEvent(OrderCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println(event + " SENT");
    }
}
