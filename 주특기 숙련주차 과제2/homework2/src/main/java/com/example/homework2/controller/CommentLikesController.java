package com.example.homework2.controller;

import com.example.homework2.service.CommentLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/commentlikes")
@RequiredArgsConstructor
public class CommentLikesController {

    private final CommentLikesService commentLikesService;

    @PutMapping("/{id}")
    public boolean CommentLikes(@PathVariable Long id, HttpServletRequest httpServletRequest){
        return commentLikesService.CommentLikes(id, httpServletRequest);
    }
}
