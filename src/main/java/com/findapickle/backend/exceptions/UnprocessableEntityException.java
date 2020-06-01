package com.findapickle.backend.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
    private static final long serialVersionUID = 1004L;
    public UnprocessableEntityException(){
        super("Bad Value");
    }
    public UnprocessableEntityException(String message){
        super(message);
    }
}