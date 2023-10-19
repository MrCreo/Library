package com.example.Library.services;

import com.example.Library.dto.Book.BookDTO;
import com.example.Library.models.Book;
import com.example.Library.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAvailableBooks() {
        return bookRepo.findAvailableBooks();
    }

    public List<Book> getOnHandBooks() {
        return bookRepo.findOnHandBooks();
    }

    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    public Book create(BookDTO bookDto) {
        return bookRepo.save(bookDto.mapToBook());
    }

    public Book update(BookDTO bookDto, Long id) {
        return bookRepo.findById(id)
                .map(book -> {
                    book.setName(bookDto.getName());
                    book.setAuthor(bookDto.getAuthor());
                    book.setYear(bookDto.getYear());
                    return bookRepo.save(book);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Данная книга не найдена"));
    }

    public void delete(Long id) {
        bookRepo.delete(id);
    }
}
