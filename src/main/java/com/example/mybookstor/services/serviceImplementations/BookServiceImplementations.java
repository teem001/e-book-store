package com.example.mybookstor.services.serviceImplementations;

//import com.example.mybookstor.config.cloudinary.CloudinaryConfig;
//import com.example.mybookstor.config.cloudinary.CloudinaryUtil;
import com.example.mybookstor.entities.Book;
import com.example.mybookstor.repositories.BookRepository;
import com.example.mybookstor.requests.BookRequest;
import com.example.mybookstor.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImplementations implements BookService {
    private final BookRepository bookRepository;

//    private final CloudinaryConfig config;
//
//    private final CloudinaryUtil cloudinaryUtil;



    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public String addABookToCollection(BookRequest bookRequest)
    {

        Book book = new Book();
        BeanUtils.copyProperties(bookRequest,book);
        String bookPath = "";

        Book savedBook=   bookRepository.save(book);
//        try
//        {
//            bookPath = cloudinaryUtil.createOrUpdateImage(bookRequest.getBookImagePath(), savedBook);
//
//        }
//        catch (IOException e)
//        {
//            e.getMessage();
//
//            }
//            if (bookPath == null) return "operation  unsuccessful due to network";
//
//            book.setImagePath(bookPath);
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
