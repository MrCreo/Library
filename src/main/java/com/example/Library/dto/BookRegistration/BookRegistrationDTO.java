package com.example.Library.dto.BookRegistration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BookRegistrationDTO {
    @NotNull()
    @Positive
    private Long personId;
    @NotNull()
    @Positive
    private Long bookId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
