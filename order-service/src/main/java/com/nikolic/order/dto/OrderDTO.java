package com.nikolic.order.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private String orderStatus;
}
