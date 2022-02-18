package com.web.home.book.model.dto;


public class FilterInfo {

    private int from;
    private int count = 5;
    private String filterBy;
    private String keyword;

    public FilterInfo() {
    }

    public FilterInfo(int from, PRODUCT_FILTER_TYPE type, String keyword) {
        this.from = from * count;
        this.keyword = keyword;
        setType(type);
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setType(PRODUCT_FILTER_TYPE type) {
        this.filterBy = type.getDB();
    }
}
