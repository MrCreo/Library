package com.example.Library.repos;

import com.example.Library.models.Book;
import com.example.Library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Long> {
    @Query("select b from Book b where b.id in (select " +
            "br.bookId " +
            "from BookRegistration br " +
            "where br.personId = :personId " +
            "group by br.bookId " +
            "having (" +
            "sum(case when br.status = 0 then 1 else 0 end) - sum(case when br.status = 1 then 1 else 0 end) > 0))")
    List<Book> getOnHandBooks(@Param("personId") Long id);

    @Query("select b from Book b where b.id in (select " +
            "br.bookId " +
            "from BookRegistration br " +
            "where br.personId = :personId " +
            "group by br.bookId " +
            "having (" +
            "sum(case when br.status = 0 then 1 else 0 end) - sum(case when br.status = 1 then 1 else 0 end) = 0))")
    List<Book> getReturnedBooks(@Param("personId") Long personId);

    @Modifying
    @Transactional
    @Query("delete from Person p where p.id = :id")
    void delete(@Param("id") Long id);

}
