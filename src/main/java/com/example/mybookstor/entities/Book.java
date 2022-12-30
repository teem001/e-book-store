package com.example.mybookstor.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Setter
@Getter
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String isbn;
    private String bookTitle;
    private String author;
    private Long bookPrice;
    private String genre;
    private String imagePath;
}
