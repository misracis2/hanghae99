package com.example.homework1.controller;

import com.example.homework1.dto.PostsListResponseDto;
import com.example.homework1.dto.PostResponseDto;
import com.example.homework1.dto.RequestDto;
import com.example.homework1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public PostsListResponseDto getPosts() {
        return postService.getPosts();
    }

    @PostMapping("/post")
    public PostResponseDto creatPost(@RequestBody RequestDto requestDto){
        return postService.creatPost(requestDto);
    }
}
