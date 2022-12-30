package com.example.mybookstor.utils;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Constants {
    private final static String USER_NOT_FOUND ="User not found";
    private final static String USER_EXITS = "user already exist";


    public Constants(){}
}
