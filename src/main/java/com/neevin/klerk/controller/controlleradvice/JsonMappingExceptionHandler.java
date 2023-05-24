package com.neevin.klerk.controller.controlleradvice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.neevin.klerk.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class JsonMappingExceptionHandler {
    @ExceptionHandler({JsonMappingException.class, JsonParseException.class})
    protected ResponseEntity<MessageDto> handleJsonParsingError(Exception exc) {
        return new ResponseEntity<>(new MessageDto(exc.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<MessageDto> onUnrecognizedPropertyExceptionException(
            UnrecognizedPropertyException exc
    ) {
        return new ResponseEntity<>(new MessageDto("недопустимое поле %s".formatted(exc.getPropertyName())), HttpStatus.BAD_REQUEST);
    }
}


