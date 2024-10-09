package com.nikolic.catalog.mapper;

import com.nikolic.catalog.dto.BookDto;
import com.nikolic.catalog.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto bookToBookDto (Book book);

    @Mapping(target = "availability", ignore = true)
    Book bookDtoToBook(BookDto bookDto);

    List<BookDto> bookListToBookDtoList(List<Book> bookList);

    @Mapping(target = "id", ignore = true)
    void updateBookFromDto(BookDto bookDto, @MappingTarget Book book);
}
