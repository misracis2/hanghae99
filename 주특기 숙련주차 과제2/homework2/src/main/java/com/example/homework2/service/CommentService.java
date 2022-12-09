package com.example.homework2.service;

import com.example.homework2.dto.CommentDeleteResponseDto;
import com.example.homework2.dto.CommentRequsetDto;
import com.example.homework2.dto.CommentResponseDto;
import com.example.homework2.entity.Comment;
import com.example.homework2.entity.Post;
import com.example.homework2.entity.User;
import com.example.homework2.repository.CommentRepository;
import com.example.homework2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TokenCheck tokenCheck;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto saveComment(Long postid, CommentRequsetDto commentRequsetDto, HttpServletRequest request) {
        User user = tokenCheck.tokenChecking(request);
        Post post = postRepository.findByIdAndUsername(postid, user.getUsername());
        if(post != null){
        Comment comment = new Comment(commentRequsetDto,  post, user.getUsername());
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getComment(), comment.getUsername());
        }
        throw new IllegalArgumentException("게시글이 존재하지 않습니다");
    }
    @Transactional
    public CommentResponseDto updateComment(Long postid, Long commentid, CommentRequsetDto commentRequsetDto, HttpServletRequest request) {
        User user = tokenCheck.tokenChecking(request);
        Post post = postRepository.findByIdAndUsername(postid, user.getUsername());
        if(post == null){
            throw new IllegalArgumentException("게시글이 존재하지 않습니다");
        }
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        comment.update(commentRequsetDto);
        return new CommentResponseDto(comment.getComment(), comment.getUsername());
    }
    @Transactional
    public CommentDeleteResponseDto deleteComment(Long postid, Long commentid, HttpServletRequest request) {
        User user = tokenCheck.tokenChecking(request);
        Post post = postRepository.findByIdAndUsername(postid, user.getUsername());
        if(post == null){
            throw new IllegalArgumentException("게시글이 존재하지 않습니다");
        }
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        commentRepository.deleteById(commentid);
        return new CommentDeleteResponseDto("삭제 완료", HttpStatus.OK.value());
    }
}
