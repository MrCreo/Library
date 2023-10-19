package com.example.Library.repos.BookRegistration;

import com.example.Library.models.BookRegistration;
import org.springframework.data.repository.query.Param;

public interface BookRegistrationCustomRepo {
    BookRegistration getLastBookRegistration(@Param("bookId") Long bookId);
}
