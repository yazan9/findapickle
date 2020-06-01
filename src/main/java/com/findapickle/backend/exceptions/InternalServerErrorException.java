package com.findapickle.backend.exceptions;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InternalServerErrorException extends RuntimeException {
    private static final long serialVersionUID = 1004L;
    public InternalServerErrorException(){
        super("Internal Server Error");
    }
    public InternalServerErrorException(String message){
        super(message);
    }
}