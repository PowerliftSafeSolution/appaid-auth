package com.powerliftsafesolution.appaid.ws.utils;

import com.powerliftsafesolution.appaid.ws.exceptions.MissingRequiredFieldException;
import com.powerliftsafesolution.appaid.ws.shared.dto.UserDTO;
import com.powerliftsafesolution.appaid.ws.ui.models.response.ErrorMessages;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class UserProfileUtils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvxyz";

    private String generateRandomString(int length){
        StringBuilder returnValue = new StringBuilder(length);

        for(int i = 0; i < length; i++){
            returnValue.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }

        return new String(returnValue);
    }

    public String generateUserId(int length){
        return this.generateRandomString(length);
    }

    public void validateRequiredFields(UserDTO userDto) throws MissingRequiredFieldException{

        if (userDto.getFirstName() == null || userDto.getFirstName().isEmpty() ||
                userDto.getLastName() == null || userDto.getLastName().isEmpty() ||
                userDto.getEmail() == null || userDto.getEmail().isEmpty() ||
                userDto.getPassword() == null || userDto.getPassword().isEmpty() ||
                userDto.getRole() == null || userDto.getRole().isEmpty() ||
                userDto.getUserName() == null || userDto.getUserName().isEmpty() ||
                userDto.getGender() == null || userDto.getGender().isEmpty() ||
                userDto.getEmployeeId() <= 0){
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
    }
}