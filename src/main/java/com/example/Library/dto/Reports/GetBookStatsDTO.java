package com.example.Library.dto.Reports;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class GetBookStatsDTO {
    @NotNull
    private Date from;
    @NotNull
    private Date to;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
