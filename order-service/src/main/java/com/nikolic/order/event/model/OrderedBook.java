package com.nikolic.order.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderedBook {
    private String bookId;
    private Integer quantity;
}
