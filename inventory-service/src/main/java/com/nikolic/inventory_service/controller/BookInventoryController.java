package com.nikolic.inventory_service.controller;

import com.nikolic.inventory_service.dto.BookQuantityDTO;
import com.nikolic.inventory_service.dto.BookInventoryDto;
import com.nikolic.inventory_service.service.BookInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class BookInventoryController {
    private final BookInventoryService bookInventoryService;

    @GetMapping("/{bookId}")
    public BookInventoryDto getAvailableCopies(@PathVariable Long bookId) {
        return bookInventoryService.getAvaliableCopies(bookId);
    }

    @PostMapping("/{bookId}")
    public BookInventoryDto addBook(@PathVariable  Long bookId, @RequestBody BookQuantityDTO bookQuantityDTO) {
        return bookInventoryService.addBook(bookId, bookQuantityDTO);
    }

    @DeleteMapping("/{bookId}")
    public BookInventoryDto removeBook(@PathVariable Long bookId, @RequestBody BookQuantityDTO request) {
        BookInventoryDto updatedInventory = bookInventoryService.removeBook(bookId, request);
        return updatedInventory;
    }

}
