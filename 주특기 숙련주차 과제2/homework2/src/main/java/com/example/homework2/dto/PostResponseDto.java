package com.example.homework2.dto;

import com.example.homework2.entity.Comment;
import com.example.homework2.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {

    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Comment> commentList;
    public PostResponseDto(Post post){
        title = post.getTitle();
        username = post.getUsername();
        content = post.getContent();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
    }
    public PostResponseDto(Post post, List<Comment> commentList){
        title = post.getTitle();
        username = post.getUsername();
        content = post.getContent();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
        this.commentList =commentList;
    }
}
