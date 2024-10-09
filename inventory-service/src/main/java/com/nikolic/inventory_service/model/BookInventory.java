package com.nikolic.inventory_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_inventory")
@Getter
@Setter
public class BookInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    private Integer availableCopies;


}
