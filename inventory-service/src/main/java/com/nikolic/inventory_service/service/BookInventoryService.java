package com.nikolic.inventory_service.service;

import com.nikolic.inventory_service.dto.BookInventoryDto;
import com.nikolic.inventory_service.mapper.BookInventoryMapper;
import com.nikolic.inventory_service.model.BookInventory;
import com.nikolic.inventory_service.repository.BookInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInventoryService {

    private BookInventoryRepository bookInventoryRepository;

    public BookInventoryDto getAvaliableCopies(String bookId) {
        BookInventory bookInventory = bookInventoryRepository.findByBookId(bookId);
        return BookInventoryMapper.INSTANCE.inventoryBookToInventoryBookDto(bookInventory);
    }

}
