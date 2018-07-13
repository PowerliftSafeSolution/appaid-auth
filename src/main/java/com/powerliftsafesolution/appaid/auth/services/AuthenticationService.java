package com.powerliftsafesolution.appaid.ws.services;

import com.powerliftsafesolution.appaid.ws.exceptions.AuthenticationFailedException;
import com.powerliftsafesolution.appaid.ws.shared.dto.UserDTO;

public interface AuthenticationService {

    UserDTO authenticate(String userName, String password) throws AuthenticationFailedException;
    String issueAccessToken(UserDTO userProfile) throws AuthenticationFailedException;
}
