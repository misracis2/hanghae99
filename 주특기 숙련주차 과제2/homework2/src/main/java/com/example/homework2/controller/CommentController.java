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
    @PostMapping("/{postid}/comment")
    public CommentResponseDto saveComment(@PathVariable Long postid, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.saveComment(postid, commentRequestDto, request);
    }
    //댓글 수정
    @PutMapping("/{postid}/{commentid}")
    public CommentResponseDto updateComment(@PathVariable Long postid, @PathVariable Long commentid, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.updateComment(postid, commentid, commentRequestDto, request);
    }
    //댓글 삭제
    @DeleteMapping("/{postid}/{commentid}")
    public CommentDeleteResponseDto deleteComment(@PathVariable Long postid, @PathVariable Long commentid, HttpServletRequest request) {
        return commentService.deleteComment(postid, commentid, request);
    }
}
