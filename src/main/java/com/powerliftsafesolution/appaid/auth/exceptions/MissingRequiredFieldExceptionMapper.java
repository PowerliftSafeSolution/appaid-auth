package com.powerliftsafesolution.appaid.ws.exceptions;

import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessageResponseModel;
import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingRequiredFieldExceptionMapper implements ExceptionMapper<MissingRequiredFieldException> {

    @Override
    public Response toResponse(MissingRequiredFieldException exception) {

        ErrorMessageResponseModel errorMessageResponseModel = new ErrorMessageResponseModel(exception.getMessage(),
                ErrorMessages.MISSING_REQUIRED_FIELD.name());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessageResponseModel).build();
    }
}