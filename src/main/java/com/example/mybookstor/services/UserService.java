package com.example.mybookstor.services;

import com.example.mybookstor.entities.UserEntity;
import com.example.mybookstor.enums.Role;
import com.example.mybookstor.requests.UserRequest;
import com.example.mybookstor.response.LoginDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    String createNewUser(UserRequest userRequest);
    String logIn(LoginDto login);
    Map<Role,List<UserEntity> > getAllUser();
}
