package com.powerliftsafesolution.appaid.ws.io.morphia.dao.impl;

import com.powerliftsafesolution.appaid.ws.io.IOUtils;
import com.powerliftsafesolution.appaid.ws.io.morphia.dao.UserDAO;
import com.powerliftsafesolution.appaid.ws.io.morphia.entity.UserEntity;
import com.powerliftsafesolution.appaid.ws.shared.dto.UserDTO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl extends BasicDAO<UserEntity, ObjectId> implements UserDAO {

    @Autowired
    private IOUtils ioUtils;

    @Autowired
    public UserDAOImpl(Datastore datastore){
        super(UserEntity.class, datastore);
    }

    @Override
    public UserDTO getByUserName(String userName) {
        UserDTO userDTO = null;

        Query<UserEntity> query = createQuery().field("userName").equal(userName);
        UserEntity userEntity = query.get();

        if(userEntity != null){
            userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
        }

        return userDTO;
    }

    @Override
    public UserDTO getByEmail(String email) {
        UserDTO userDTO = null;

        Query<UserEntity> query = createQuery().field("email").equal(email);
        UserEntity userEntity = query.get();

        if(userEntity != null){
            userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
        }

        return userDTO;
    }

    @Override
    public UserDTO getByUserId(String userID) {
        UserDTO userDTO = null;

        Query<UserEntity> query = createQuery().field("userId").equal(userID);
        UserEntity userEntity = query.get();

        if(userEntity != null){
            userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
        }

        return userDTO;
    }

    @Override
    public UserDTO getByEmployeeId(int employeeId) {
        UserDTO userDTO = null;

        Query<UserEntity> query = createQuery().field("employeeId").equal(employeeId);
        UserEntity userEntity = query.get();

        if(userEntity != null){
            userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
        }

        return userDTO;
    }

    @Override
    public List<UserDTO> getByFirstName(String firstName) {
        List<UserDTO> userDTOS = null;

        Query<UserEntity> query = createQuery().field("firstName").equal(firstName);
        List<UserEntity> userEntities = query.asList();

        if(userEntities != null){
            userDTOS = ioUtils.copyToUserDTOList(userEntities);
        }
        return userDTOS;
    }

    @Override
    public List<UserDTO> getByLastName(String lastName) {
        List<UserDTO> userDTOS = null;

        Query<UserEntity> query = createQuery().field("lastName").equal(lastName);
        List<UserEntity> userEntities = query.asList();

        if(userEntities != null){
            userDTOS = ioUtils.copyToUserDTOList(userEntities);
        }
        return userDTOS;
    }

    @Override
    public List<UserDTO> getByRole(String role) {
        List<UserDTO> userDTOS = null;

        Query<UserEntity> query = createQuery().field("role").equal(role);
        List<UserEntity> userEntities = query.asList();

        if(userEntities != null){
            userDTOS = ioUtils.copyToUserDTOList(userEntities);
        }
        return userDTOS;
    }

}
