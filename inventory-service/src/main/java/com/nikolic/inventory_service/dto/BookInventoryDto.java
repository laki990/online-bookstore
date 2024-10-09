package com.nikolic.inventory_service.dto;

import lombok.Data;

@Data
public class BookInventoryDto {

    Long id;
    Long bookId;
    Integer availableCopies;
}
