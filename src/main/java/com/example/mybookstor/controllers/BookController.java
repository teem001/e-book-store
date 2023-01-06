package com.example.mybookstor.controllers;

import com.example.mybookstor.entities.Book;
import com.example.mybookstor.requests.BookRequest;
import com.example.mybookstor.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

        Date time = new Date(System.currentTimeMillis());

        model.addAttribute("books",books);
        model.addAttribute("time", time);

        return "home-page";

    }


    @PostMapping("/add")
    public  String addBookToStore(Model model)
    {

        BookRequest bookRequest = new BookRequest();//TODO this and update should have th same implementation for the sake of the model attribute

        model.addAttribute("addBook", bookRequest);

        return "admin_add_book";

    }

    @GetMapping("/add")
    public String saveBookToDB(@ModelAttribute("addBook") BookRequest newBook)
    {
        bookService.addABookToCollection(newBook);

        return "redirect:/books";
    }
    @PutMapping("/update")//TODO this and addBookTOStore must have the same implementation for the sake of model attribute
    public String updateBook(@RequestParam("bookId")long bookId, Model model)
    {
        Book book = bookService.getABook(bookId);

        model.addAttribute("book", book);

        return"update";

    }

    @DeleteMapping("/delete")
    public String deleteABook(@RequestParam("bookId")long bookId)
    {
        bookService.deleteABook(bookId);

        return "redirect:/books";
    }
}
