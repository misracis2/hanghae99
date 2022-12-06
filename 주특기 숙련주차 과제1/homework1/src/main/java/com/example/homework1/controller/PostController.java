package com.example.homework1.controller;

import com.example.homework1.dto.DeleteResponseDto;
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

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.updatePost(id, requestDto,request);
    }

    @DeleteMapping("/post/{id}")
    public DeleteResponseDto deletePost(@PathVariable Long id, HttpServletRequest request){
        return postService.deletePost(id,request);
    }
}
