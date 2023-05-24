package com.neevin.klerk.exception;

import org.springframework.http.HttpStatus;

public class ArticleBanException extends BaseException{
        public ArticleBanException() {
            super("Article could not be banned because of internal server error");
            setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
