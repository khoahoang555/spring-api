package com.smart.exchange.orderservice.service.validation;

import com.smart.exchange.orderservice.service.dto.ErrorNotFound;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    private List<ErrorNotFound> listErrorNotFound;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(List<ErrorNotFound> listErrorNotFound) {
        this.listErrorNotFound = listErrorNotFound;
    }
}
