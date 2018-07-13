package com.powerliftsafesolution.appaid.ws.services.impl;

import com.powerliftsafesolution.appaid.ws.exceptions.AuthenticationFailedException;
import com.powerliftsafesolution.appaid.ws.io.morphia.dao.UserDAO;
import com.powerliftsafesolution.appaid.ws.io.morphia.dao.impl.UserDAOImpl;
import com.powerliftsafesolution.appaid.ws.io.morphia.entity.UserEntity;
import com.powerliftsafesolution.appaid.ws.io.morphia.config.MorphiaConfig;
import com.powerliftsafesolution.appaid.ws.services.AuthenticationService;
import com.powerliftsafesolution.appaid.ws.shared.dto.UserDTO;
import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessages;
import com.powerliftsafesolution.appaid.ws.utils.PasswordUtils;
import com.powerliftsafesolution.appaid.ws.utils.UserProfileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordUtils passwordUtils;

    @Autowired
    private UserProfileUtils userProfileUtils;

    public AuthenticationServiceImpl(){
    }

    @Override
    public UserDTO authenticate(String userName, String password) throws AuthenticationFailedException{

        UserDTO storedUser = this.userDAO.getByUserName(userName) != null ?
                this.userDAO.getByUserName(userName) : this.userDAO.getByEmail(userName);

        if(storedUser == null){
            throw new AuthenticationFailedException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }

        if(!passwordUtils.verifyPassword(password, storedUser.getEncryptedPassword())){
            throw new AuthenticationFailedException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }

        return storedUser;
    }

    @Override
    public String issueAccessToken(UserDTO userProfile) throws AuthenticationFailedException{

        String tokenSalt =  this.userProfileUtils.generateUserId(16);
        String accessTokenMaterial = tokenSalt + userProfile.getUserId();
        String completeToken = this.passwordUtils.hashPassword(accessTokenMaterial);

        int completeTokenLength = completeToken.length();

        String tokenPartToStoreInDb = completeToken.substring(0, completeTokenLength/2);
        String returnValue = completeToken.substring(completeTokenLength/2, completeTokenLength);


        userProfile.setToken(tokenPartToStoreInDb);
        userProfile.setTokenSalt(tokenSalt);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userProfile, userEntity);

        this.userDAO.save(userEntity);

        return returnValue;
    }
}
