package com.example.homework2.service;

import com.example.homework2.dto.DeleteResponseDto;
import com.example.homework2.dto.PostsListResponseDto;
import com.example.homework2.dto.PostResponseDto;
import com.example.homework2.dto.PostRequestDto;
import com.example.homework2.entity.Post;
import com.example.homework2.entity.User;
import com.example.homework2.jwt.JwtUtil;
import com.example.homework2.repository.PostRepository;
import com.example.homework2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        throw new IllegalArgumentException("로그인이 필요합니다");
    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto,HttpServletRequest request) {
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
            //게시글 수정 리턴값에 modifiedAt 값이 최신화되지 않는 문제
            Post post = postRepository.findByIdAndUsername(id, user.getUsername());
            if(post != null){
                post.update(requestDto);
                return new PostResponseDto(post);
            }else{
                throw new IllegalArgumentException("올바른 사용자가 아닙니다");
            }
        }throw new IllegalArgumentException("로그인이 필요합니다");
    }

    public DeleteResponseDto deletePost(Long id, HttpServletRequest request) {
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
            Post post = postRepository.findByIdAndUsername(id, user.getUsername());
            if(post != null){
                postRepository.deleteById(id);
                return new DeleteResponseDto("삭제를 완료했습니다", HttpStatus.OK.value());
            }
        }throw new IllegalArgumentException("로그인이 필요합니다");
    }
}
