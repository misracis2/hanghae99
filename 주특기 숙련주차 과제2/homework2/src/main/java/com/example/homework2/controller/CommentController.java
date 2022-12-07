package com.example.homework2.controller;

import com.example.homework2.dto.CommentRequsetDto;
import com.example.homework2.dto.CommentResponseDto;
import com.example.homework2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/save/comment/{id}")
    public CommentResponseDto saveComment(@PathVariable Long id, @RequestBody CommentRequsetDto commentRequsetDto, HttpServletRequest request){
        return commentService.saveComment(id, commentRequsetDto, request);
    }
}
