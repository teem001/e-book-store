package com.example.mybookstor.controllers;

import com.example.mybookstor.entities.Book;
import com.example.mybookstor.requests.BookRequest;
import com.example.mybookstor.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController
{

    private  final  BookService bookService;

    @GetMapping()
    public String getAllBooks(Model model)
    {
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("books",books);

        return "home-page";

    }


    @PostMapping("")
    public  String addBookToStore(Model model)
    {

        BookRequest bookRequest = new BookRequest();//TODO this and update should have th same implementation for the sake of the model attribute

        model.addAttribute("addBook", bookRequest);

        return "admin_add_book";

    }

    @GetMapping("")
    public String saveBookToDB(@ModelAttribute("addBook") BookRequest newBook)
    {
        bookService.addABookToCollection(newBook);

        return "redirect:/books";
    }
    @PutMapping()//TODO this and addBookTOStore must have the same implementation for the sake of model attribute
    public String updateBook(@RequestParam("bookId")long bookId, Model model)
    {
        Book book = bookService.getABook(bookId);

        model.addAttribute("book", book);

        return"";

    }

    @DeleteMapping()
    public String deleteABook(@RequestParam("bookId")long bookId)
    {
        bookService.deleteABook(bookId);

        return "redirect:/books";
    }
}
