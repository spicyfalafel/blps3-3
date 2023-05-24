package com.neevin.klerk.exception;

import com.neevin.klerk.entity.User;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException(){
        super("User not found");
    }
    public UserNotFoundException(String s) {
        super(s);
    }
}
