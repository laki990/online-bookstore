package com.nikolic.catalog.service;

import com.nikolic.catalog.dto.BookDto;
import com.nikolic.catalog.exception.BookNotFoundException;
import com.nikolic.catalog.mapper.BookMapper;
import com.nikolic.catalog.model.Book;
import com.nikolic.catalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final BookRepository bookRepository;

    public void addBook(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        bookRepository.save(book);
    }

    public List<BookDto> getAllBooks () {
        var allBoks =bookRepository.findAll();
        return BookMapper.INSTANCE.bookListToBookDtoList(allBoks);
    }

    public void deleteBook(Long id) {
        var existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(existingBook);
    }

    public BookDto getBook(@RequestParam Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return BookMapper.INSTANCE.bookToBookDto(book);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        var existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        BookMapper.INSTANCE.updateBookFromDto(bookDto, existingBook);
        bookRepository.save(existingBook);

        return BookMapper.INSTANCE.bookToBookDto(existingBook);
    }
}
