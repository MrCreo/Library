package com.example.Library.dto.Person;

import com.example.Library.models.Person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class PersonDTO {


    @NotBlank
    private String f;

    @NotBlank
    private String i;
    private String o;

    @Past
    @NotNull
    private Date birthDate;

    public Person mapToPerson() {
        Person person = new Person();
        person.setF(this.getF());
        person.setI(this.getI());
        person.setO(this.getO());
        person.setBirthDate(this.getBirthDate());
        return person;
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
