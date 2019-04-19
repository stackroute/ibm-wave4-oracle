package com.stackroute.manualservice.exception;

public class QueryAlreadyExistException extends Exception{


    public QueryAlreadyExistException() {
    }

    public QueryAlreadyExistException(String message) {
        super(message);
    }
}
