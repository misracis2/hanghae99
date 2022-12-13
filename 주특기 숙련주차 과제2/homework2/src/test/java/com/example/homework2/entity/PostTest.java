package com.example.homework2.entity;

import com.example.homework2.dto.PostRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void createPost(){
        Long id = 1L;
        String title = "테스트 게시글입니다";
        String username = null;
        String content = "테스트가 잘 될랑가";

        PostRequestDto postRequestDto = new PostRequestDto();

        Post post = new Post(postRequestDto, username);

        assertEquals(username, post.getUsername());
    }

}