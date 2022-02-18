package com.web.home.advice;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.web.home.advice.exception.CDBException;
import com.web.home.book.model.dto.ExceptionResponse;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExceptionResponse exHandler(Exception e) {
        System.out.println("[Exception] = " + e);
        return new ExceptionResponse(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "알 수 없는 서버 오류가 발생하였습니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionResponse exHandler(MethodArgumentTypeMismatchException e, HttpServletRequest req) {
        System.out.println("[Exception] = " + e);
        return new ExceptionResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "검색 타입이 정확하지 않습니다. = " + req.getParameter("type"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionResponse exHandler(MissingServletRequestParameterException e, HttpServletRequest req) {
        System.out.println("[Exception] = " + e);
        return new ExceptionResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                req.getParameter("type") + "을 입력해야합니다.");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExceptionResponse exHandler(CDBException e) {
        System.out.println("[Exception] = " + e);
        return new ExceptionResponse(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Database에서 데이터를 가져오는데 실패하였습니다.");
    }


}