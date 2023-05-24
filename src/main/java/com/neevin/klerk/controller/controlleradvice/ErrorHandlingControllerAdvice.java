package com.neevin.klerk.controller.controlleradvice;

import com.neevin.klerk.dto.MessageDto;
import com.neevin.klerk.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @Getter
    @AllArgsConstructor
    public static class ValidationErrorResponse {
        private List<Violation> violations;
        public ValidationErrorResponse(){
            this.violations = new ArrayList<Violation>();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Violation {
        private final String fieldName;
        private final String message;
    }


    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException exc
    ) {
        final List<Violation> violations = exc.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException exc
    ) {
        final List<Violation> violations = exc.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onHttpMessageNotReadableException(HttpMessageNotReadableException e){
        ValidationErrorResponse error = new ValidationErrorResponse();
        String message = e.getMessage();
        assert message != null;
        var field = Pattern.compile("\"(.*?)\"")
                .matcher(message).results().map(mr -> mr.group(1)).findFirst();
        if (field.isPresent()) {
            error.getViolations().add(new Violation(field.get(), e.getMessage()));
        } else {
            error.getViolations().add(new Violation("unrecognized field", e.getMessage()));
        }
        return error;
    }

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<?> handleMyException(BaseException ex, WebRequest request) {
        if (ex.getMessage() !=null) return new ResponseEntity<>(new MessageDto(ex.getMessage()), ex.getStatus());
        else return new ResponseEntity<>(ex.getStatus());
    }
}
