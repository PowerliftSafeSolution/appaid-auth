package com.powerliftsafesolution.appaid.ws.exceptions;

public class NoRecordFoundException extends RuntimeException {

    private static final long serialVersionUID = -7804780806112653975L;

    public NoRecordFoundException(String message){
        super(message);
    }
}
