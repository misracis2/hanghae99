package com.example.homework2.dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String comment;
    private String username;

    public CommentResponseDto(String comment, String username) {
        this.comment = comment;
        this.username = username;
    }
}
