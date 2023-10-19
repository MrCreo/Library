package com.example.Library.services;

import com.example.Library.dto.Person.PersonDTO;
import com.example.Library.models.Book;
import com.example.Library.models.Person;
import com.example.Library.repos.PersonRepo;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepo personRepo;


    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> findAll() {
        return personRepo.findAll(Sort.by(Sort.Direction.DESC, "birthDate"));
    }

    public Person create(PersonDTO personDto) {
        return personRepo.save(personDto.mapToPerson());
    }

    public Person update(PersonDTO personDTO, Long id) {
        return personRepo.findById(id)
                .map(person -> {
                    person.setF(personDTO.getF());
                    person.setI(personDTO.getI());
                    person.setO(personDTO.getO());
                    person.setBirthDate(personDTO.getBirthDate());
                    return personRepo.save(person);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Данный клиент не найден"));
    }

    public void delete(Long id) {
        personRepo.delete(id);
    }

    private void checkPersonExist(Long personId) {
        personRepo.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Клиент библиотеки не найден"));
    }

    public List<Book> getOnHandBooks(Long personId) {
        this.checkPersonExist(personId);
        return personRepo.getOnHandBooks(personId);
    }

    public List<Book> getReturnedBooks(Long personId) {
        this.checkPersonExist(personId);
        return personRepo.getReturnedBooks(personId);
    }
}
