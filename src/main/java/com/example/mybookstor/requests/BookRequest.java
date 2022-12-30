package com.example.mybookstor.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BookRequest {
    private String isbn;
    private String bookTitle;
    private String author;
    private Long bookPrice;
}
