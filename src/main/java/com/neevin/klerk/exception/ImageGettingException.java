package com.neevin.klerk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Сan't get an image")
public class ImageGettingException extends BaseException {
    public ImageGettingException(String message) {
        super(message);
    }
}
