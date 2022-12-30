package com.example.mybookstor.services;

import com.example.mybookstor.entities.Book;
import com.example.mybookstor.requests.BookRequest;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    String addABookToCollection(BookRequest bookRequest);

    Book getABook(long bookId);
    String updateABook(BookRequest bookRequest);
    String deleteABook(Long BookId);
}
