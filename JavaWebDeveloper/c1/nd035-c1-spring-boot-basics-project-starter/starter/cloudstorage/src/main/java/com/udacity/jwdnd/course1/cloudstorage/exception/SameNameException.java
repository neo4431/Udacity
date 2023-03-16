package com.udacity.jwdnd.course1.cloudstorage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Duplicate name!!!")
public class SameNameException extends RuntimeException {
    public SameNameException(String msg) {
        super(msg);
    }
}
