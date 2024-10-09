package com.nikolic.catalog.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String author;
    String genre;
    Double price;
    @Enumerated(EnumType.STRING)
    Availability availability;
}
