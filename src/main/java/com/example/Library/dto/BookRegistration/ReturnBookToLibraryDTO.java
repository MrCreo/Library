package com.example.Library.dto.BookRegistration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReturnBookToLibraryDTO {
    @NotNull
    @Positive
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
