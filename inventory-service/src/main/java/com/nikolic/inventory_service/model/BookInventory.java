package com.nikolic.inventory_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book_inventory")
@Data
public class BookInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookId;

    private Integer availableCopies;


}
