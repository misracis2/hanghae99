package com.example.homework2.dto;

import com.example.homework2.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class PostResponseDto {

    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post){
        title = post.getTitle();
        username = post.getUsername();
        content = post.getContent();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
    }
}
