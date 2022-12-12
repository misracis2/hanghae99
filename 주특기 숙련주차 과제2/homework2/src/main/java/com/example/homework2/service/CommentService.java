package com.example.homework2.service;

import com.example.homework2.dto.CommentDeleteResponseDto;
import com.example.homework2.dto.CommentRequestDto;
import com.example.homework2.dto.CommentResponseDto;
import com.example.homework2.entity.Comment;
import com.example.homework2.entity.Post;
import com.example.homework2.entity.User;
import com.example.homework2.jwt.JwtUtil;
import com.example.homework2.repository.CommentRepository;
import com.example.homework2.repository.PostRepository;
import com.example.homework2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class CommentService {

    private final JwtUtil jwtUtil;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentResponseDto saveComment(Long postid, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        //로그인 여부 확인
        User user = tokenChecking(request);
        //게시글 저장 여부 확인
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        //댓글 저장
        Comment comment = new Comment(commentRequestDto,  post, user.getUsername());
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getContent());
    }
    @Transactional
    public CommentResponseDto updateComment(Long postid, Long commentid, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        //로그인 여부 확인
        User user = tokenChecking(request);
        //게시글 존재 여부 확인
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        //댓글 존재 여부 확인
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        //댓글 업데이트
        comment.update(commentRequestDto);
        //수정된 댓글 반환
        return new CommentResponseDto(comment.getContent());
    }

    public CommentDeleteResponseDto deleteComment(Long postid, Long commentid, HttpServletRequest request) {
        //로그인 여부 확인
        User user = tokenChecking(request);
        //게시글 저장 여부 확인
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        //댓글 저장 여부 확인
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        //댓글 삭제
        commentRepository.deleteById(commentid);
        //삭제 완료 반환
        return new CommentDeleteResponseDto("삭제 완료", HttpStatus.OK.value());
    }
    //로그인 여부 확인
    public User tokenChecking(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );
            return user;
        }throw new IllegalArgumentException("로그인이 필요합니다");

    }
}
