package com.nikolic.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookInventoryDto {

    Long id;
    Long bookId;
    Integer availableCopies;
}
