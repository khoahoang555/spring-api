package com.example.springcodeapi.service.validation;

import com.example.springcodeapi.service.errors.HedgeGroupNameExistException;
import com.example.springcodeapi.service.errors.HedgeGroupNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HedgeGroupNameExistException.class)
    public ResponseEntity<Object> exception(HedgeGroupNameExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HedgeGroupNotFound.class)
    public ResponseEntity<Object> exception(HedgeGroupNotFound exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
