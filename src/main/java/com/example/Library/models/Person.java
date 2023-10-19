package com.example.Library.models;

import com.example.Library.jsonView.PersonView;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Person")
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(PersonView.GetPerson.class)
    private Long id;

    @Column(name = "f")
    @JsonView(PersonView.GetPerson.class)
    private String f;

    @Column(name = "i")
    @JsonView(PersonView.GetPerson.class)
    private String i;

    @Column(name = "o")
    @JsonView(PersonView.GetPerson.class)
    private String o;

    @Column(name = "birthDate")
    @JsonView(PersonView.GetPerson.class)
    private Date birthDate;

    @OneToMany(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "person_id")
    private List<BookRegistration> bookRegistrationList;

    public List<BookRegistration> getBookRegistrationList() {
        return bookRegistrationList;
    }

    public void setBookRegistrationList(List<BookRegistration> bookRegistrationList) {
        this.bookRegistrationList = bookRegistrationList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
