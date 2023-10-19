package com.example.Library.models;

import com.example.Library.Enums.BookStatus;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "BookRegistration")
@Table(name = "book_registration")
public class BookRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    @Convert(converter = BookStatus.MemberStatusEnumConverter.class)
    private BookStatus status;

    @Column(name = "person_id")
    private Long personId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person Person;

    @Column(name = "book_id")
    private Long bookId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book Book;

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public com.example.Library.models.Person getPerson() {
        return Person;
    }

    public void setPerson(com.example.Library.models.Person person) {
        Person = person;
    }

    public com.example.Library.models.Book getBook() {
        return Book;
    }

    public void setBook(com.example.Library.models.Book book) {
        Book = book;
    }
}
