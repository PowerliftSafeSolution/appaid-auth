package com.powerliftsafesolution.appaid.ws.exceptions;


import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessageResponseModel;
import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoRecordFoundExceptionMapper implements ExceptionMapper<NoRecordFoundException> {

    @Override
    public Response toResponse(NoRecordFoundException exception) {

        ErrorMessageResponseModel errorMessageResponseModel = new ErrorMessageResponseModel(exception.getMessage(),
                ErrorMessages.NO_RECORD_FOUND.name());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessageResponseModel).build();
    }
}
