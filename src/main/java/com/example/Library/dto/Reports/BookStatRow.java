package com.example.Library.dto.Reports;

import java.util.Date;

public class BookStatRow {
    private Date date;
    private Long countTaken;

    private Long countReturned;

    public BookStatRow(Date date, Long countTaken, Long countReturned) {
        this.date = date;
        this.countTaken = countTaken;
        this.countReturned = countReturned;
    }

    public Long getCountReturned() {
        return countReturned;
    }

    public void setCountReturned(Long countReturned) {
        this.countReturned = countReturned;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCountTaken() {
        return countTaken;
    }

    public void setCountTaken(Long countTaken) {
        this.countTaken = countTaken;
    }
}
