package com.example.mybookstor.repositories;

import com.example.mybookstor.entities.UserEntity;
import com.example.mybookstor.requests.UserRequest;
import com.example.mybookstor.response.LoginDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailAndPhone(UserRequest userRequest);
    Optional<UserEntity> findByEmailAndPassword(LoginDto loginDto);

    Optional<UserEntity> findByEmail(String email);
}
