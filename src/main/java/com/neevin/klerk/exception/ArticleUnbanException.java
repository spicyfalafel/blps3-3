package com.neevin.klerk.exception;

import org.springframework.http.HttpStatus;

public class ArticleUnbanException extends BaseException {

    public ArticleUnbanException() {
        super("Article could not be unbanned because of internal server error");
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
