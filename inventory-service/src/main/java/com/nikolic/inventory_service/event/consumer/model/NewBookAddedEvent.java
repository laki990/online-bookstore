package com.nikolic.inventory_service.event.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBookAddedEvent {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private Double price;
}

