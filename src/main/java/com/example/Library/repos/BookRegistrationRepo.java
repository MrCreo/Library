package com.example.Library.repos;

import com.example.Library.dto.Reports.BookStatRow;
import com.example.Library.models.Book;
import com.example.Library.models.BookRegistration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookRegistrationRepo extends JpaRepository<BookRegistration, Long> {

    @Query("select " +
            "case when b.id is null then false else true end " +
            "from Book b " +
            "where " +
            "b.id in (select " +
            "br.bookId " +
            "from BookRegistration br " +
            "where br.bookId = :bookId " +
            "group by br.bookId " +
            "having sum(case when br.status = 0 then 1 else 0 end) - sum(case when br.status = 1 then 1 else 0 end) = 0) " +
            "or (select count(*) from BookRegistration br where br.bookId = :bookId) = 0")
    Boolean IsBookAvailable(@Param("bookId") Long bookId);

    @Query("select br from BookRegistration br where br.bookId = :bookId order by br.date desc")
    List<BookRegistration> getBookRegistrationByBookId(@Param("bookId") Long bookId, Pageable pageable);

    @Query("select new com.example.Library.dto.Reports.BookStatRow(" +
            "date_trunc('day',br.date)," +
            "sum(case when br.status = 0 then 1 else 0 end)," +
            "sum(case when br.status = 1 then 1 else 0 end)" +
            ") " +
            "from BookRegistration br " +
            "where br.date > :from " +
            "and br.date < :to " +
            "group by date_trunc('day',br.date) " +
            "order by date_trunc('day',br.date) desc ")
    List<BookStatRow> getBooksStats(@Param("from") Date from, @Param("to") Date to);

    @Query("select b " +
            "from Book b " +
            "where b.id in (select br.bookId " +
            "from BookRegistration br " +
            "group by br.bookId " +
            "order by sum(case when br.status = 0 then 1 else 0 end) desc" +
            ")")
    List<Book> getTopBookList(Pageable pageable);
}
