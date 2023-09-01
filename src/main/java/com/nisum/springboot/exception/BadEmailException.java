package com.nisum.springboot.exception;

public class BadEmailException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BadEmailException(String message) {
        super(message);
    }
}
