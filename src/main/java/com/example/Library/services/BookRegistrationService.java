package com.example.Library.services;

import com.example.Library.Enums.BookStatus;
import com.example.Library.dto.BookRegistration.BookRegistrationDTO;
import com.example.Library.models.BookRegistration;
import com.example.Library.repos.BookRegistrationRepo;
import com.example.Library.repos.BookRepo;
import com.example.Library.repos.PersonRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Objects;

@Service
public class BookRegistrationService {
    private final BookRegistrationRepo bookRegistrationRepo;
    private final BookRepo bookRepo;
    private final PersonRepo personRepo;

    public BookRegistrationService(BookRegistrationRepo bookRegistrationRepo, BookRepo bookRepo, PersonRepo personRepo) {
        this.bookRegistrationRepo = bookRegistrationRepo;
        this.bookRepo = bookRepo;
        this.personRepo = personRepo;
    }

    private void checkBookExist(Long bookId) {
        bookRepo.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Книга не найдена"));
    }

    private void checkPersonExist(Long personId) {
        personRepo.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Клиент библиотеки не найден"));
    }

    public void giveBookToPerson(BookRegistrationDTO bookRegistrationDTO) {
        this.checkBookExist(bookRegistrationDTO.getBookId());
        this.checkPersonExist(bookRegistrationDTO.getPersonId());

        final Boolean isBookAvailable = bookRegistrationRepo.IsBookAvailable(bookRegistrationDTO.getBookId());
        if (isBookAvailable == null || Boolean.FALSE.equals(isBookAvailable))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Данная книга уже выдана");

        final BookRegistration newRegistration = new BookRegistration();
        newRegistration.setBookId(bookRegistrationDTO.getBookId());
        newRegistration.setPersonId(bookRegistrationDTO.getPersonId());
        newRegistration.setDate(new Date());
        newRegistration.setStatus(BookStatus.OnHands);
        bookRegistrationRepo.save(newRegistration);
    }

    public void returnBook(BookRegistrationDTO bookRegistrationDTO) {
        this.checkBookExist(bookRegistrationDTO.getBookId());
        this.checkPersonExist(bookRegistrationDTO.getPersonId());

        final Boolean isBookAvailable = bookRegistrationRepo.IsBookAvailable(bookRegistrationDTO.getBookId());
        if (Boolean.TRUE.equals(isBookAvailable))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Данная книга уже в библиотеке");

        final Pageable pageRequest = PageRequest.of(0, 1);
        final BookRegistration lastRegistartion = bookRegistrationRepo.getBookRegistrationByBookId(bookRegistrationDTO.getBookId(), pageRequest)
                .get(0);
        if (lastRegistartion == null || !Objects.equals(lastRegistartion.getPersonId(), bookRegistrationDTO.getPersonId()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Данную книгу брал на прокат не данный клиент");

        final BookRegistration newReg = new BookRegistration();
        newReg.setDate(new Date());
        newReg.setPersonId(bookRegistrationDTO.getPersonId());
        newReg.setBookId(bookRegistrationDTO.getBookId());
        newReg.setStatus(BookStatus.InLibrary);

        bookRegistrationRepo.save(newReg);
    }
}
