package com.example.homework2.controller;

import com.example.homework2.dto.CommentDeleteResponseDto;
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


    @PostMapping("/post/{postid}/comment")
    public CommentResponseDto saveComment(@PathVariable Long postid, @RequestBody CommentRequsetDto commentRequsetDto, HttpServletRequest request){
        return commentService.saveComment(postid, commentRequsetDto, request);
    }

    @PutMapping("/post/{postid}/{commentid}")
    public CommentResponseDto updateComment(@PathVariable Long postid, @PathVariable Long commentid, @RequestBody CommentRequsetDto commentRequsetDto, HttpServletRequest request){
        return commentService.updateComment(postid, commentid, commentRequsetDto, request);
    }

    @DeleteMapping("/post/{postid}/{commentid}")
    public CommentDeleteResponseDto deleteComment(@PathVariable Long postid, @PathVariable Long commentid, HttpServletRequest request) {
        System.out.println(postid + commentid);
        return commentService.deleteComment(postid, commentid, request);
    }
}
