package com.example.Library.repos;

import com.example.Library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.id in " +
            "(select " +
            "br.bookId " +
            "from BookRegistration br " +
            "group by br.bookId " +
            "having (" +
            "sum(case when br.status = 0 then 1 else 0 end) - sum(case when br.status = 1 then 1 else 0 end) = 0)) " +
            "or (select count(*) from BookRegistration br where br.bookId = b.id) = 0")
    List<Book> findAvailableBooks();

    @Query("select b from Book b where b.id in (select " +
            "br.bookId " +
            "from BookRegistration br " +
            "group by br.bookId " +
            "having (" +
            "sum(case when br.status = 0 then 1 else 0 end) - sum(case when br.status = 1 then 1 else 0 end) > 0))")
    List<Book> findOnHandBooks();

    @Modifying
    @Transactional
    @Query("delete from Book b where b.id = :id")
    void delete(@Param("id") Long id);
}
