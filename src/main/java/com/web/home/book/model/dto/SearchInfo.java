package com.web.home.book.model.dto;


public class SearchInfo {

    private int from;
    private int count = 5;
    private String searchBy;
    private String keyword;

    public SearchInfo() {
    }

    public SearchInfo(int from, PRODUCT_SEARCH_TYPE type, String keyword) {
        this.from = from * count;
        this.keyword = keyword;
        setSearchBy(type);
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setSearchBy(PRODUCT_SEARCH_TYPE type) {
        this.searchBy = type.getTableName();
    }
}
