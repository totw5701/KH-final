package com.web.home.book.model.dto;


public class PagingInfo {

    private int from;
    private int count = 5;

    public PagingInfo() {
    }

    public PagingInfo(int from) {
        this.from = from * count;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}
