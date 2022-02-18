package com.web.home.book.model.dto;

public enum PRODUCT_SEARCH_TYPE {
    WRITER("PRO_WRITER"),
    TITLE("PRO_TITLE"),
    PUBLISHER("PRO_PUBLISHER"),
    CATEGORY("PRO_CATEGORY");

    private final String db;

    PRODUCT_SEARCH_TYPE(String db) {
        this.db = db;
    }

    public String getTableName() {
        return db;
    }
}
