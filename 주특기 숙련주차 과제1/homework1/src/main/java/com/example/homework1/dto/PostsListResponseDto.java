package com.example.homework1.dto;

import com.example.homework1.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostsListResponseDto extends PostsResponseDto{

    List<PostsResponseDto> PostsList = new ArrayList<>();

    public PostsListResponseDto(Post post) {
        super(post);
    }

    public void addPosts(PostsResponseDto postsResponseDto) {
        PostsList.add(postsResponseDto);
    }

}
