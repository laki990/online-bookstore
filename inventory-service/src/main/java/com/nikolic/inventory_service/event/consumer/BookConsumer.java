package com.nikolic.inventory_service.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolic.inventory_service.event.consumer.model.NewBookAddedEvent;
import com.nikolic.inventory_service.model.BookInventory;
import com.nikolic.inventory_service.repository.BookInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookConsumer {

   private final ObjectMapper objectMapper;
   private final BookInventoryRepository bookInventoryRepository;

    @KafkaListener(topics = "new-book", groupId = "inventory-group")
    public void consumeMessage(String message) {

        try {
            NewBookAddedEvent newBookAddedEvent = objectMapper.readValue(message, NewBookAddedEvent.class);
            String bookId = newBookAddedEvent.getBookId();
            BookInventory bookInventory = new BookInventory();
            bookInventory.setBookId(bookId);
            bookInventory.setAvailableCopies(0);

            bookInventoryRepository.save(bookInventory);

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

    }
}
