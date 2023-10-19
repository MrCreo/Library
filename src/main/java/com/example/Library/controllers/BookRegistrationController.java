package com.example.Library.controllers;

import com.example.Library.dto.BookRegistration.BookRegistrationDTO;
import com.example.Library.services.BookRegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("BookRegistration")
public class BookRegistrationController {
    private final BookRegistrationService bookRegistrationService;

    public BookRegistrationController(BookRegistrationService bookRegistrationService) {
        this.bookRegistrationService = bookRegistrationService;
    }

    @PostMapping("TakeBook")
    public void takeBook(@Valid @RequestBody BookRegistrationDTO bookRegistrationDTO) {
        bookRegistrationService.giveBookToPerson(bookRegistrationDTO);
    }

    @PostMapping("ReturnBook")
    public void returnBook(@Valid @RequestBody BookRegistrationDTO bookRegistrationDTO) {
        bookRegistrationService.returnBook(bookRegistrationDTO);
    }
}
