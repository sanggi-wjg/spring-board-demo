package com.example.demo.common.exception;

public class AlreadyExist extends RuntimeException {

    public AlreadyExist(String message) {
        super(message);
    }
}
