package com.example.homework2.controller;

import com.example.homework2.dto.CommentDeleteResponseDto;
import com.example.homework2.dto.CommentRequestDto;
import com.example.homework2.dto.CommentResponseDto;
import com.example.homework2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/{postId}")
    public CommentResponseDto saveComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.saveComment(postId, commentRequestDto, request);
    }
    //댓글 수정
    @PutMapping("/{postId}/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.updateComment(postId, commentId, commentRequestDto, request);
    }
    //댓글 삭제
    @DeleteMapping("/{postId}/{commentId}")
    public CommentDeleteResponseDto deleteComment(@PathVariable Long postId, @PathVariable Long commentId, HttpServletRequest request) {
        return commentService.deleteComment(postId, commentId, request);
    }
}
