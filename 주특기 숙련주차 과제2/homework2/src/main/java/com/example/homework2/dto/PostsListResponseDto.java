package com.example.homework2.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostsListResponseDto {

    List<PostResponseDto> PostsList = new ArrayList<>();

    public void addPosts(PostResponseDto postsResponseDto) {
        PostsList.add(postsResponseDto);
    }

}
