package com.example.homework1.service;

import com.example.homework1.dto.PostsListResponseDto;
import com.example.homework1.dto.PostResponseDto;
import com.example.homework1.dto.PostRequestDto;
import com.example.homework1.entity.Post;
import com.example.homework1.entity.User;
import com.example.homework1.jwt.JwtUtil;
import com.example.homework1.repository.PostRepository;
import com.example.homework1.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional
    public PostsListResponseDto getPosts() {
        PostsListResponseDto postsListResponseDto = new PostsListResponseDto();
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        for(Post post : posts) {
            postsListResponseDto.addPosts(new PostResponseDto(post));
        }
        return postsListResponseDto;
    }
    @Transactional
    public PostResponseDto creatPost(PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if(token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );
            Post post = new Post(requestDto, user.getUsername());
            postRepository.save(post);
            return new PostResponseDto(post);
        }
        return null; //무엇을 반환해야할까? PostResponseDto에 에러메시지를 담아 보낼까?
    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        return new PostResponseDto(post);
    }
}
