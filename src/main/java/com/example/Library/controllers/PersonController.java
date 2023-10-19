package com.example.Library.controllers;

import com.example.Library.dto.Person.PersonDTO;
import com.example.Library.jsonView.PersonView;
import com.example.Library.models.Book;
import com.example.Library.models.Person;
import com.example.Library.services.PersonService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @JsonView(PersonView.GetPerson.class)
    @GetMapping
    public List<Person> getAll() {
        return personService.findAll();
    }

    @JsonView(PersonView.GetPerson.class)
    @PostMapping
    public Person create(@Valid @RequestBody PersonDTO personDTO) {
        return personService.create(personDTO);
    }

    @JsonView(PersonView.GetPerson.class)
    @PutMapping("{id}")
    public Person update(@Valid @RequestBody PersonDTO personDTO, @PathVariable("id") Long id) {
        return personService.update(personDTO, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

    @GetMapping("{id}/OnHandBooks")
    public List<Book> getOnHandBooks(@PathVariable Long id) {
        return personService.getOnHandBooks(id);
    }

    @GetMapping("{id}/ReturnedBooks")
    public List<Book> getReturnedBooks(@PathVariable("id") Long id) {
        return personService.getReturnedBooks(id);
    }


}
