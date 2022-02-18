package com.web.home.advice.exception;

public class CDBException extends RuntimeException{

    public CDBException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public CDBException(String msg) {
        super(msg);
    }

    public CDBException() {
        super();
    }
}
