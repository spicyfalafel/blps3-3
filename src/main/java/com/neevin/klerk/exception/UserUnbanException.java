package com.neevin.klerk.exception;

import org.springframework.http.HttpStatus;

public class UserUnbanException extends BaseException{
    public UserUnbanException() {
        super("User could not be unbanned because of internal server error");
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
