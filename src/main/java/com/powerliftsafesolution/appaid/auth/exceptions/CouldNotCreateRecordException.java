package com.powerliftsafesolution.appaid.ws.exceptions;

public class CouldNotCreateRecordException extends RuntimeException {

    private static final long serialVersionUID = 4753608718403867351L;

    public CouldNotCreateRecordException(String message){
        super(message);
    }
}
