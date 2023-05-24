package com.neevin.klerk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "can't save an image")
public class ImageSavingException extends BaseException {

    public ImageSavingException(String message) {
        super(message);
    }

    public ImageSavingException(){
        super("Can't save an image");
    }

}
