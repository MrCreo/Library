package com.example.Library.dto.Book;

import com.example.Library.models.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BookDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String author;
    @Positive
    @NotNull
    private Long year;

    public Book mapToBook() {
        Book book = new Book();
        book.setAuthor(this.getAuthor());
        book.setName(this.getName());
        book.setYear(this.getYear());
        return book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
}
