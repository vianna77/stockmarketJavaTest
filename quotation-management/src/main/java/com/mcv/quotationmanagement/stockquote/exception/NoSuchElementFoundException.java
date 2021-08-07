package com.mcv.quotationmanagement.stockquote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchElementFoundException extends ResponseStatusException {

    public NoSuchElementFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
