package com.nisum.springboot.exception;

public class AlreadyRegisteredException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
