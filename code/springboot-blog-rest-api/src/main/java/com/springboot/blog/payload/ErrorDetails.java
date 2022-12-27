package com.smart.exchange.orderservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
//    private String message;
    private String details;
    private List<String> messages;

    public ErrorDetails(Date timestamp, List<String> messages, String details) {
        this.timestamp = timestamp;
        this.messages = messages;
        this.details = details;
    }

//    public ErrorDetails(List<String> messages) {
//        this.messages = messages;
//    }
}
