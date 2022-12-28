package com.springboot.blog.payload;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

public class ErrorDetails {
    private HttpStatus status;
    private Date timestamp;
    private List<Object> message;
    private String details;

    public ErrorDetails(HttpStatus status, Date timestamp, List<Object> message, String details) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<Object> getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
