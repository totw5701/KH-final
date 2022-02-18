package com.web.home.book.model.dto;

public enum PRODUCT_FILTER_TYPE {
    COUNTRY("PRO_COUNTRY");

    private final String db;

    PRODUCT_FILTER_TYPE(String db) {
        this.db = db;
    }

    public String getDB() {
        return db;
    }
}
