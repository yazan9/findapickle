package com.findapickle.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1004L;
    public NotFoundException(){
        super("Entity Not Found");
    }
    public NotFoundException(String message){
        super(message);
    }
}