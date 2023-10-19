package com.example.Library.services;

import com.example.Library.dto.Reports.BookStatRow;
import com.example.Library.dto.Reports.GetBookStatsDTO;
import com.example.Library.models.Book;
import com.example.Library.repos.BookRegistrationRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {
    private final BookRegistrationRepo bookRegistrationRepo;

    public ReportsService(BookRegistrationRepo bookRegistrationRepo) {
        this.bookRegistrationRepo = bookRegistrationRepo;
    }

    public List<BookStatRow> getBooksStats(GetBookStatsDTO getBookStatsDTO) {
        return bookRegistrationRepo.getBooksStats(getBookStatsDTO.getFrom(), getBookStatsDTO.getTo());
    }

    public List<Book> getTop5Books() {
        final Pageable pageable = PageRequest.of(0, 5);
        return bookRegistrationRepo.getTopBookList(pageable);
    }
}
