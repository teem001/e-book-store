package com.example.mybookstor.entities;

import com.example.mybookstor.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "uses")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private  String password;
    @Column(unique = true)
    private String email;
    private String phone;
    private String imgPath;
    @Enumerated(EnumType.STRING)
    private Role role;

}
