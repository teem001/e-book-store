package com.example.mybookstor.repositories;

import com.example.mybookstor.entities.Book;
import com.example.mybookstor.entities.UserEntity;
import com.example.mybookstor.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository  underTest;

    @Test
    void findByEmailAndPhone() {
        //given
        UserEntity user = new UserEntity(
                1L,
                "olayinka",
                "huzain",
                "123456789",
                "email@email",
               "12345",
               "hello.jpg",
               Role.USER
        );
        underTest.save(user);
        //when
        boolean exist = underTest.findByEmailAndPhone(user.getEmail(), user.getPhone()).isPresent();

        //test
        assertThat(exist).isTrue();

    }
    @Test
    void findByEmailAndPassword() {
        //given
        UserEntity user = new UserEntity(
                2L,
                "olayinka",
                "huzain",
                "123456789",
                "email@email",
                "12345",
                "hello.jpg",
                Role.USER
        );
        underTest.save(user);
        //when

        UserEntity userEntityOptional = underTest.findByEmailAndPassword(user.getEmail(),user.getPassword()).get();

        assertThat(userEntityOptional.getEmail()).isEqualTo("email@email");
        assertThat(userEntityOptional.getUserId()).isEqualTo(2L);
        assertThat(userEntityOptional.getFirstName()).isEqualTo("olayinka");
        assertThat(userEntityOptional.getLastName()).isEqualTo("huzain");
        assertThat(userEntityOptional.getPassword()).isEqualTo("123456789");
        assertThat(userEntityOptional.getImgPath()).isEqualTo("hello.jpg");
        assertThat(userEntityOptional.getPhone()).isEqualTo("12345");


    }

    @Test
    void findByEmail() {
        //given
        UserEntity user = new UserEntity(
                1L,
                "olayinka",
                "huzain",
                "123456789",
                "email@email",
                "12345",
                "hello.jpg",
                Role.USER
        );
        underTest.save(user);
        boolean exist = underTest.findByEmail(user.getEmail()).isPresent();

        assertThat(exist).isTrue();

    }
}