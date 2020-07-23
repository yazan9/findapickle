package com.findapickle.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1005L;
    public ForbiddenException(){
        super("Forbidden");
    }
    public ForbiddenException(String message){
        super(message);
    }
}
