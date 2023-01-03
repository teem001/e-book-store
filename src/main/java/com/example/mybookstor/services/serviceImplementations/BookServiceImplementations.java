package com.example.mybookstor.services.serviceImplementations;

import com.example.mybookstor.entities.Book;
import com.example.mybookstor.repositories.BookRepository;
import com.example.mybookstor.requests.BookRequest;
import com.example.mybookstor.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImplementations implements BookService {
    private final BookRepository bookRepository;


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public String addABookToCollection(BookRequest bookRequest) {
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest,book);
        bookRepository.save(book);
        return "success";

    }

    @Override
    public String updateABook(BookRequest bookRequest) {
        return null;
    }

    @Override
    public String deleteABook(Long bookId)
    {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new RuntimeException("Book does not exixt"));

        return "success";
    }

    @Override
    public Book getABook(long bookId)
    {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book not available"));
        return book;
    }
}
