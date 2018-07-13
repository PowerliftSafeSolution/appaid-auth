package com.powerliftsafesolution.appaid.ws.exceptions;

import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessageResponseModel;
import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationFailExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {

    @Override
    public Response toResponse(AuthenticationFailedException exception) {
        ErrorMessageResponseModel errorMessageResponseModel = new ErrorMessageResponseModel(exception.getMessage(),
                ErrorMessages.AUTHENTICATION_FAILED.name());

        return Response.status(Response.Status.UNAUTHORIZED).entity(errorMessageResponseModel).build();
    }
}
