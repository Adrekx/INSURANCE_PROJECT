package com.expensemanager.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ItemAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID=1l;
    public ItemAlreadyExistsException(String message)
    {
        super(message);
    }
}
