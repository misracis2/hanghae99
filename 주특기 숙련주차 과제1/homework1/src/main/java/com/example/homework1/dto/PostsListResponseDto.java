package com.example.homework1.dto;

import com.example.homework1.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostsListResponseDto {

    List<PostResponseDto> PostsList = new ArrayList<>();

    public void addPosts(PostResponseDto postsResponseDto) {
        PostsList.add(postsResponseDto);
    }

}
