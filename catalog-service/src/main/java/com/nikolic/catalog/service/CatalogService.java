package com.nikolic.catalog.service;

import com.nikolic.catalog.dto.BookDto;
import com.nikolic.catalog.event.model.NewBookAddedEvent;
import com.nikolic.catalog.event.producer.BookEventProducer;
import com.nikolic.catalog.exception.BookNotFoundException;
import com.nikolic.catalog.mapper.BookMapper;
import com.nikolic.catalog.model.Availability;
import com.nikolic.catalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogService {

    private final BookRepository bookRepository;
    private final BookEventProducer bookEventProducer;

    public void addBook(BookDto bookDto) {
        var book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        book.setAvailability(Availability.IN_STOCK);
        var newBook = bookRepository.saveAndFlush(book);

        NewBookAddedEvent event = new NewBookAddedEvent(
                newBook.getId(),
                newBook.getTitle(),
                newBook.getAuthor(),
                newBook.getGenre(),
                newBook.getPrice()
        );
        bookEventProducer.send(event);
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
