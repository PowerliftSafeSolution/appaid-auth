package com.powerliftsafesolution.appaid.ws.ui.models.response;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields."),
    RECORD_ALREADY_EXISTS("Record already exists."),
    INTERAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("No record found"),
    AUTHENTICATION_FAILED("Authentication failed");

    private String errorMessage;

    ErrorMessages(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}