package com.smart.exchange.orderservice.service.validation;

import com.smart.exchange.orderservice.service.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                         WebRequest webRequest) {
        List<String> listErrors = new ArrayList<>();

        if (exception.getListErrorNotFound().isEmpty()) {
            listErrors.add(exception.getMessage());
        } else {
            exception.getListErrorNotFound().stream().forEach(errorNotFound -> {
                String message = String.format("%s not found with %s : '%s'", errorNotFound.getResourceName(),
                        errorNotFound.getFieldName(), errorNotFound.getFieldValue());
                listErrors.add(message);
            });
        }

        ErrorDetails errorDetails = new ErrorDetails(new Date(), listErrors,
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
