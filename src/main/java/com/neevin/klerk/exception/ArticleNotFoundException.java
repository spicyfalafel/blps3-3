package com.neevin.klerk.exception;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Article not found")
public class ArticleNotFoundException extends BaseException {

    public ArticleNotFoundException(String message) {
        super(message);
        this.setStatus(HttpStatus.NOT_FOUND);
    }

    public ArticleNotFoundException(){
        super("Article not found");
        this.setStatus(HttpStatus.NOT_FOUND);
    }

}
