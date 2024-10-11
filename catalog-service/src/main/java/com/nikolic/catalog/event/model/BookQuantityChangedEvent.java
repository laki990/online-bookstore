package com.nikolic.catalog.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookQuantityChangedEvent {

    private Long bookId;
    private Integer totalCount;
}
