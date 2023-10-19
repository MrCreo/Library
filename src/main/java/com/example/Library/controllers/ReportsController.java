package com.example.Library.controllers;

import com.example.Library.dto.Reports.BookStatRow;
import com.example.Library.dto.Reports.GetBookStatsDTO;
import com.example.Library.models.Book;
import com.example.Library.services.ReportsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Report")
public class ReportsController {
    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @PostMapping("GetStats")
    public List<BookStatRow> getBookStats(@Valid @RequestBody GetBookStatsDTO getBookStatsDTO) {
        return reportsService.getBooksStats(getBookStatsDTO);
    }

    @PostMapping("GetTopFiveBooks")
    public List<Book> getTop5Books() {
        return reportsService.getTop5Books();
    }
}
