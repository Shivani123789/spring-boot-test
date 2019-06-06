package com.stackroute.muzixservice.exception;

public class TrackAlreadyExistsException extends Exception {
    private String message1;

    public TrackAlreadyExistsException(String message)
    {
        super(message);
        this.message1=message;
    }
}
