package com.powerliftsafesolution.appaid.ws.exceptions;

import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessageResponseModel;
import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable exception) {

        ErrorMessageResponseModel errorMessageResponseModel = new ErrorMessageResponseModel(exception.getMessage(),
                ErrorMessages.INTERAL_SERVER_ERROR.name());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessageResponseModel).build();
    }
}
