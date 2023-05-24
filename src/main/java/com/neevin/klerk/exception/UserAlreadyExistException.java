package com.neevin.klerk.exception;

public class UserAlreadyExistException extends BaseException {
    public UserAlreadyExistException(){
        super("User already exist");
    }
}
