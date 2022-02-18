package com.web.home.book.model.dto;

import java.time.LocalDateTime;

public class RecentInfo {

    private int from;
    private int count = 5;
    private LocalDateTime date;

    public RecentInfo(int from, LocalDateTime date) {
        this.from = from;
        this.date = date;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
