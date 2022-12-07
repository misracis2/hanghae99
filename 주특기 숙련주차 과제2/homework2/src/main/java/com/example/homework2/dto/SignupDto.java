package com.example.homework2.dto;

import lombok.Getter;

@Getter
public class SignupDto {

    private String msg;
    private int statuscode;

    public SignupDto(String msg, int statuscode){
        this.msg = msg;
        this.statuscode = statuscode;
    }
}
