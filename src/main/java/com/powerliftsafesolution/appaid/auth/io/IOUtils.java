package com.powerliftsafesolution.appaid.ws.io;

import com.powerliftsafesolution.appaid.ws.io.morphia.entity.UserEntity;
import com.powerliftsafesolution.appaid.ws.shared.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IOUtils {

    public List<UserDTO> copyToUserDTOList(List<UserEntity> userEntities){
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();

        for(UserEntity userEntity: userEntities){
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            userDTOS.add(userDTO);
        }

        return  userDTOS;
    }
}
