package com.example.homework1.service;

import com.example.homework1.dto.PostsListResponseDto;
import com.example.homework1.dto.PostsResponseDto;
import com.example.homework1.entity.Post;
import com.example.homework1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    @Transactional
    public PostsListResponseDto getPosts() {
        PostsListResponseDto postsListResponseDto = new PostsListResponseDto();
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        for(Post post : posts) {
            postsListResponseDto.addPosts(new PostsResponseDto(post));
        }
        return postsListResponseDto;
    }
}
