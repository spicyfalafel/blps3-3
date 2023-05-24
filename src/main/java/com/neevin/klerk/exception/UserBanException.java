package com.neevin.klerk.exception;

import org.springframework.http.HttpStatus;

public class UserBanException extends BaseException{
    public UserBanException() {
        super("User could not be banned because of internal server error");
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
