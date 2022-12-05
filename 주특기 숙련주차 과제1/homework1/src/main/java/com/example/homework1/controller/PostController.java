package com.example.homework1.controller;

import com.example.homework1.dto.PostsListResponseDto;
import com.example.homework1.dto.PostsResponseDto;
import com.example.homework1.entity.Post;
import com.example.homework1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public PostsListResponseDto getPosts() {
        return postService.getPosts();
    }
}
