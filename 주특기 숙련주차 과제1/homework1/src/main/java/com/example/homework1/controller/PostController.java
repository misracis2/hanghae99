package com.example.homework1.controller;

import com.example.homework1.dto.PostsListResponseDto;
import com.example.homework1.dto.PostResponseDto;
import com.example.homework1.dto.PostRequestDto;
import com.example.homework1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public PostResponseDto creatPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.creatPost(requestDto, request);
    }

}
