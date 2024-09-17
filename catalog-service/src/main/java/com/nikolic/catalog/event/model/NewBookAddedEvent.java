package com.nikolic.catalog.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBookAddedEvent {
    private Long bookId;
    private String title;
    private String author;
    private String genre;
    private Double price;
}

