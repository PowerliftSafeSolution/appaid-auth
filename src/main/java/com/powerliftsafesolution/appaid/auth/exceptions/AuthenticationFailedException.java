package com.powerliftsafesolution.appaid.ws.exceptions;

public class AuthenticationFailedException extends RuntimeException {

    private static final long serialVersionUID = -2594220831203281429L;

    public AuthenticationFailedException(String message){
        super(message);
    }
}
