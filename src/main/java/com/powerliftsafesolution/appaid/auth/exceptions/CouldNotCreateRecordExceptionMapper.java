package com.powerliftsafesolution.appaid.ws.exceptions;

import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessageResponseModel;
import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CouldNotCreateRecordExceptionMapper implements ExceptionMapper<CouldNotCreateRecordException> {

    @Override
    public Response toResponse(CouldNotCreateRecordException exception) {

        ErrorMessageResponseModel errorMessageResponseModel = new ErrorMessageResponseModel(exception.getMessage(),
                ErrorMessages.RECORD_ALREADY_EXISTS.name());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessageResponseModel).build();
    }
}

