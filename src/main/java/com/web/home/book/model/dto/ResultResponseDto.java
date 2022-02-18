package com.web.home.book.model.dto;

import com.web.home.book.model.BookDTO;

import java.util.List;

public class ResultResponseDto {

    private PagingDto pagingDto;
    private List<BookDTO> list;

    public ResultResponseDto() {
    }

    public ResultResponseDto(PagingDto pagingDto, List<BookDTO> list) {
        this.pagingDto = pagingDto;
        this.list = list;
    }

    public PagingDto getPagingDto() {
        return pagingDto;
    }

    public void setPagingDto(PagingDto pagingDto) {
        this.pagingDto = pagingDto;
    }

    public List<BookDTO> getList() {
        return list;
    }

    public void setList(List<BookDTO> list) {
        this.list = list;
    }
}