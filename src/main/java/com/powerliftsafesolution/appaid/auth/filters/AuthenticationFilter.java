package com.powerliftsafesolution.appaid.ws.filters;

import com.powerliftsafesolution.appaid.ws.annotations.Secured;
import com.powerliftsafesolution.appaid.ws.exceptions.AuthenticationFailedException;
import com.powerliftsafesolution.appaid.ws.services.UsersService;
import com.powerliftsafesolution.appaid.ws.services.impl.UsersServiceImpl;
import com.powerliftsafesolution.appaid.ws.shared.dto.UserDTO;
import com.powerliftsafesolution.appaid.ws.utils.PasswordUtils;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")){
            throw new AuthenticationFailedException("Authorization header must be provided");
        }

        String token = authorizationHeader.substring("Bearer".length()).trim();

        String userId = containerRequestContext.getUriInfo().getPathParameters().getFirst("id");

        validateToken(token, userId);
    }

    private void validateToken(String token, String userId) throws AuthenticationFailedException{
        UsersService usersService = new UsersServiceImpl();
        PasswordUtils passwordUtils = new PasswordUtils();

        UserDTO userDTO = usersService.getUserByUserID(userId);
        String first_half = userDTO.getToken();
        String completeToken = first_half + token;

        String checkToken = userDTO.getTokenSalt()+userDTO.getUserId();

        if(!passwordUtils.verifyPassword(checkToken,completeToken)){
            throw new AuthenticationFailedException("Authorization token did not match");
        }

    }
}
