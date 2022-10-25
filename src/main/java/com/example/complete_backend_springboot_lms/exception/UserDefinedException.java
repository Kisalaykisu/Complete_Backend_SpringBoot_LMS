package com.example.complete_backend_springboot_lms.exception;

public class UserDefinedException extends RuntimeException{
    public UserDefinedException(String message) {
        super(message);
    }
}
