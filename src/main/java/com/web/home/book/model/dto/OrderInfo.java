package com.web.home.book.model.dto;

public class OrderInfo {

    private int from;
    private int count = 5;
    private String orderBy;

    public OrderInfo() {
    }

    public OrderInfo(int from, PRODUCT_ORDER_TYPE orderType) {
        this.from = from * count;
        setOrderBy(orderType);
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(PRODUCT_ORDER_TYPE orderType) {

        if(orderType == PRODUCT_ORDER_TYPE.BEST_SELLER) this.orderBy = "PRO_SALES";
        else if (orderType == PRODUCT_ORDER_TYPE.NEW_BOOK) this.orderBy = "PRO_DATE";
    }
}
