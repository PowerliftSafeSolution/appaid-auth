package com.powerliftsafesolution.appaid.ws.ui.entrypoints;

import com.powerliftsafesolution.appaid.ws.services.AuthenticationService;
import com.powerliftsafesolution.appaid.ws.services.impl.AuthenticationServiceImpl;
import com.powerliftsafesolution.appaid.ws.shared.dto.UserDTO;
import com.powerliftsafesolution.appaid.ws.ui.models.request.LoginCredentialsModel;
import com.powerliftsafesolution.appaid.ws.ui.models.response.AuthenticationDetailsModel;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authentication")
public class AuthenticationEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetailsModel userLogin(LoginCredentialsModel loginCredentialsModel){

        AuthenticationDetailsModel returnValue = new AuthenticationDetailsModel();

        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        UserDTO authenticatedUser = authenticationService.authenticate(loginCredentialsModel.getUserName(),
                loginCredentialsModel.getUserPassword());

        String accessToken = authenticationService.issueAccessToken(authenticatedUser);

        returnValue.setUserId(authenticatedUser.getUserId());
        returnValue.setToken(accessToken);

        return returnValue;
    }
}
