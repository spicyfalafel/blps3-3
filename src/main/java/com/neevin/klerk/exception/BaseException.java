package com.neevin.klerk.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private HttpStatus status;

    public BaseException() {
        super();
        this.status = HttpStatus.BAD_REQUEST;
    }

    public BaseException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
