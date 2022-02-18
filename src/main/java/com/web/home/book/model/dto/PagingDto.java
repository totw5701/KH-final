package com.web.home.book.model.dto;

public class PagingDto {

    int nowPage;
    int totalPage;
    int tupleCount;

    public PagingDto(int tupleCount, int nowPage) {
        this.nowPage = nowPage;
        this.tupleCount = tupleCount;
        this.totalPage = (int) Math.ceil(tupleCount / 5.0);
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTupleCount() {
        return tupleCount;
    }

    public void setTupleCount(int tupleCount) {
        this.tupleCount = tupleCount;
    }
}