package com.smart.exchange.orderservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorNotFound {
    private String resourceName;
    private String fieldName;
    private String fieldValue;
}
