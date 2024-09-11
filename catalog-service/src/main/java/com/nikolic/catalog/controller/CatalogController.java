package com.nikolic.catalog.controller;

import com.nikolic.catalog.dto.BookDto;
import com.nikolic.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @PostMapping
    public void addBook(@RequestBody  BookDto newBook) {
        catalogService.addBook(newBook);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        var allBooks = catalogService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        var bookDto = catalogService.getBook(id);

        return ResponseEntity.ok(bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {

        var updatedBook = catalogService.updateBook(id, bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        catalogService.deleteBook(id);
    }
}

