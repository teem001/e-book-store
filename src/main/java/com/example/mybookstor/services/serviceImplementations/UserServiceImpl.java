package com.example.mybookstor.services.serviceImplementations;

import com.example.mybookstor.entities.UserEntity;
import com.example.mybookstor.enums.Role;
import com.example.mybookstor.execptions.UserExistException;
import com.example.mybookstor.repositories.UserRepository;
import com.example.mybookstor.requests.UserRequest;
import com.example.mybookstor.response.LoginDto;
import com.example.mybookstor.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.mybookstor.enums.Role.ADMIN;
import static com.example.mybookstor.enums.Role.USER;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private  final AuthenticationManager authenticationManager;


    @Override
    public String createNewUser(UserRequest userRequest)
    {
        UserEntity user= new UserEntity();

        userRepository.findByEmailAndPhone(userRequest.getEmail(),userRequest.getPhone()).orElseThrow(()-> new UserExistException("user already exits"));

        modelMapper.map(userRequest,user);

        userRepository.save(user);

        return "successful";

    }

    @Override
    public String logIn(LoginDto login)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()));

        Optional<UserEntity> optionalUser = userRepository.findByEmailAndPassword(login.getEmail(),login.getPassword());

        return optionalUser.isPresent()? "successful": "unsuccessful";
    }

    @Override
    public Map<Role, List<UserEntity>> getAllUser()
    {
        List<UserEntity> adminUser = new ArrayList<>();

        List<UserEntity> userList = new ArrayList<>();

        Map<Role, List<UserEntity>> allUserList = new HashMap<>();

        List<UserEntity> users =userRepository.findAll();

        users. stream()
                .map(user -> {
                    if (user.getRole() == USER)
                    {
                        userList.add(user);
                        allUserList.put(USER, userList);
                    }
                    else if (user.getRole()==ADMIN)
                    {
                        adminUser.add(user);
                        allUserList.put(ADMIN, users);

                    }

               return allUserList; }).collect(Collectors.toSet());

        return allUserList;
    }
}
