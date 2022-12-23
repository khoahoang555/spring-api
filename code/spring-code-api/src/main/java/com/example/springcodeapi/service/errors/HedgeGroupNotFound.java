package com.example.springcodeapi.service.errors;

public class HedgeGroupNotFound extends RuntimeException {
    public HedgeGroupNotFound() {
        super("Hedge group not found!");
    }
}
