package com.nikolic.inventory_service.event.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookQuantityChangedEvent {

    private Long bookId;
    private Integer totalCount;
}
