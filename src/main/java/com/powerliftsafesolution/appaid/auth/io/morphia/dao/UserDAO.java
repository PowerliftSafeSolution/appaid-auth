package com.powerliftsafesolution.appaid.ws.io.morphia.dao;

import com.powerliftsafesolution.appaid.ws.io.morphia.entity.UserEntity;
import com.powerliftsafesolution.appaid.ws.shared.dto.UserDTO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.UpdateOptions;
import org.mongodb.morphia.dao.DAO;

import java.util.List;

public interface UserDAO extends DAO<UserEntity, ObjectId> {

    UserDTO getByUserName(String userName);

    UserDTO getByEmail(String email);

    UserDTO getByUserId(String UserID);

    UserDTO getByEmployeeId(int employeeId);

    List<UserDTO> getByFirstName(String firstName);

    List<UserDTO> getByLastName(String lastName);

    List<UserDTO> getByRole(String role);
}
