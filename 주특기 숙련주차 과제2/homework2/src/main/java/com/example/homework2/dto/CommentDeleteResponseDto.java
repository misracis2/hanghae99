package com.example.homework2.dto;

public class CommentDeleteResponseDto {
    private String msg;
    private int statusCode;

    public CommentDeleteResponseDto(String msg, int statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
