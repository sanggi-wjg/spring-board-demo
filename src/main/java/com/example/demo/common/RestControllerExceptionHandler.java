package com.example.demo.common;

import com.example.demo.common.exception.AlreadyExist;
import com.example.demo.common.exception.ErrorResponse;
import com.example.demo.common.exception.NotExist;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(AlreadyExist.class)
    public ErrorResponse alreadyExistException(AlreadyExist e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(NotExist.class)
    public ErrorResponse notExistException(NotExist e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
