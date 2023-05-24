package com.neevin.klerk.exception;

public class NotificationNotFound extends BaseException {
    public NotificationNotFound(){
        super("Notification not found");
    }
    public NotificationNotFound(String s) {
        super(s);
    }
}
