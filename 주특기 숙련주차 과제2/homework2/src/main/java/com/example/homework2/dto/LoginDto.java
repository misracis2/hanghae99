package com.example.homework2.dto;

import lombok.Getter;

@Getter
public class LoginDto {

    private String msg;
    private int statuscode;

    public LoginDto(String msg, int statuscode){
        this.msg = msg;
        this.statuscode = statuscode;
    }
}
