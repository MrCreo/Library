package com.example.Library.controllers;

import com.example.Library.dto.Book.BookDTO;
import com.example.Library.models.Book;
import com.example.Library.services.BookService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "Book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("Available")
    public List<Book> getAvailable() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("OnHands")
    public List<Book> getOnHand() {
        return bookService.getOnHandBooks();
    }

    @PostMapping
    public Book create(@Valid @RequestBody BookDTO bookDto) {
        return bookService.create(bookDto);
    }

    @PutMapping("{id}")
    public Book update(@Valid @RequestBody BookDTO bookDto, @PathVariable("id") Long id) {
        return bookService.update(bookDto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
