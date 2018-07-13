package com.powerliftsafesolution.appaid.ws.exceptions;

public class MissingRequiredFieldException extends RuntimeException {

    private static final long serialVersionUID = -8417371713270435446L;

    public MissingRequiredFieldException(String message){
        super(message);
    }
}
