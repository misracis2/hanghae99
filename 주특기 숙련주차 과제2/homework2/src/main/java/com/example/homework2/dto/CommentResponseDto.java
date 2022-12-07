package com.example.homework2.dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String comment;

    public CommentResponseDto(String comment) {
        this.comment = comment;
    }
}
