package com.example.homework2.controller;

import com.example.homework2.service.PostLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/like")
public class PostLikesController {

    private final PostLikesService postLikesService;

    @PutMapping("/{id}")
    public boolean PostLikes(@PathVariable Long id, HttpServletRequest httpServletRequest){
        return postLikesService.PostLikes(id, httpServletRequest);
    }


}
