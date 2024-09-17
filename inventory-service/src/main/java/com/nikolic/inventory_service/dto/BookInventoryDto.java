package com.nikolic.inventory_service.dto;

import lombok.Data;

@Data
public class BookInventoryDto {

    Long id;
    String bookId;
    Integer availableCopies;
}
