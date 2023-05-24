package com.neevin.klerk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Tag not found")
public class TagNotFoundException extends BaseException {
    public TagNotFoundException() {
        super("Tag not found");
        setStatus(HttpStatus.NOT_FOUND);
    }
}
