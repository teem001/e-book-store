package com.example.mybookstor.services.serviceImplementations;

import com.example.mybookstor.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BookServiceImplementationsTest {

    private BookServiceImplementations underTest;

    @Mock
    private BookRepository bookRepository;


    @BeforeEach
    void setUp(){
        underTest = new BookServiceImplementations(bookRepository);
    }


    @Test
    void getAllBooks() {

        //u

    }

    @Test
    @Disabled
    void addABookToCollection() {
    }

    @Test
    @Disabled
    void updateABook() {
    }

    @Test
    @Disabled
    void deleteABook() {
    }

    @Test
    @Disabled
    void getABook() {
    }
}