package com.nikolic.inventory_service.service;

import com.nikolic.inventory_service.dto.BookQuantityDTO;
import com.nikolic.inventory_service.dto.BookInventoryDto;
import com.nikolic.inventory_service.event.producer.BookQuantityChangedProducer;
import com.nikolic.inventory_service.event.producer.model.BookQuantityChangedEvent;
import com.nikolic.inventory_service.mapper.BookInventoryMapper;
import com.nikolic.inventory_service.model.BookInventory;
import com.nikolic.inventory_service.repository.BookInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInventoryService {

    private final BookInventoryRepository bookInventoryRepository;
    private final BookQuantityChangedProducer bookQuantityChangedProducer;

    public BookInventoryDto getAvaliableCopies(Long bookId) {
        return bookInventoryRepository.findByBookId(bookId)
                .map(BookInventoryMapper.INSTANCE::inventoryBookToInventoryBookDto)
                .orElseThrow(() -> new RuntimeException("Book inventory not found"));
    }

    public BookInventoryDto addBook(Long bookId, BookQuantityDTO bookQuantityDTO) {
        BookInventory bookInventory = bookInventoryRepository.findByBookId(bookId)
                .map(existingBook -> updateAvailableCopies(existingBook, bookQuantityDTO.getQuantity()))
                .orElseGet(() -> createNewBookInventory(bookId, bookQuantityDTO.getQuantity()));

        BookInventory savedBookInventory = bookInventoryRepository.save(bookInventory);

        var bookQuantityChangedEvent = new BookQuantityChangedEvent(bookId, savedBookInventory.getAvailableCopies());
        bookQuantityChangedProducer.send(bookQuantityChangedEvent);

        return BookInventoryMapper.INSTANCE.inventoryBookToInventoryBookDto(savedBookInventory);
    }

    public BookInventoryDto removeBook(Long bookId, BookQuantityDTO bookQuantityDTO) {
        BookInventory bookInventory = bookInventoryRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("Book inventory not found for book ID: " + bookId));

        if (bookInventory.getAvailableCopies() < bookQuantityDTO.getQuantity()) {
            throw new IllegalArgumentException("Insufficient book amount to remove the requested quantity");
        }

        bookInventory.setAvailableCopies(bookInventory.getAvailableCopies() - bookQuantityDTO.getQuantity());
        BookInventory updatedBookInventory = bookInventoryRepository.save(bookInventory);

        var bookQuantityChangedEvent = new BookQuantityChangedEvent(bookId, updatedBookInventory.getAvailableCopies());
        bookQuantityChangedProducer.send(bookQuantityChangedEvent);

        return BookInventoryMapper.INSTANCE.inventoryBookToInventoryBookDto(updatedBookInventory);
    }


    private BookInventory updateAvailableCopies(BookInventory existingBook, Integer quantity) {
        existingBook.setAvailableCopies(existingBook.getAvailableCopies() + quantity);
        return existingBook;
    }

    private BookInventory createNewBookInventory(Long bookId, Integer quantity) {
        BookInventory newBookInventory = new BookInventory();
        newBookInventory.setBookId(bookId);
        newBookInventory.setAvailableCopies(quantity);
        return newBookInventory;
    }

}
