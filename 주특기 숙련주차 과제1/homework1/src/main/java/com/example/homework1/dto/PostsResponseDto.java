package com.example.homework1.dto;

import com.example.homework1.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class PostsResponseDto {

    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostsResponseDto(Post post){
        title = post.getTitle();
        username = post.getUsername();
        content = post.getContent();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
    }
}
