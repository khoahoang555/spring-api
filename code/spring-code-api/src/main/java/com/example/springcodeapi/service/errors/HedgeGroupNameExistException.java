package com.example.springcodeapi.service.errors;

public class HedgeGroupNameExistException extends RuntimeException {
    public HedgeGroupNameExistException() {
        super("Hedge Group Name is already in use!");
    }
}
