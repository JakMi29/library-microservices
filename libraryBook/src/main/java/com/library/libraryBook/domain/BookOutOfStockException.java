package com.library.libraryBook.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookOutOfStockException extends RuntimeException {

    public BookOutOfStockException(String message) {
        super(message);
    }

}
