package com.nikolic.catalog.dto;

import lombok.Data;

@Data
public class BookDto {

    Long id;
    String title;
    String author;
    String genre;
    Double price;
}
