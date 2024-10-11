package com.nikolic.catalog.event.consumer;

import com.nikolic.catalog.event.model.BookQuantityChangedEvent;
import com.nikolic.catalog.model.Availability;
import com.nikolic.catalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookQuantityChangedConsumer {

    private final BookRepository bookRepository;

    @KafkaListener(topics = "book-quantity-changed", groupId = "catalog_service_group")
    public void listenBookQuantityChanged(BookQuantityChangedEvent event) {
        Long bookId = event.getBookId();
        Integer totalCount = event.getTotalCount();

        if (totalCount > 0) {
            updateBookAvailability(bookId, Availability.IN_STOCK);
        } else {
            updateBookAvailability(bookId, Availability.OUT_OF_STOCK);
        }
    }

    private void updateBookAvailability(Long bookId, Availability availability) {
        bookRepository.findById(bookId).ifPresent(existingBook -> {
            existingBook.setAvailability(availability);
            bookRepository.save(existingBook);
        });
    }
}
