package com.example.homework2.service;

import com.example.homework2.dto.CommentRequsetDto;
import com.example.homework2.dto.CommentResponseDto;
import com.example.homework2.entity.Comment;
import com.example.homework2.entity.Post;
import com.example.homework2.entity.User;
import com.example.homework2.repository.CommentRepository;
import com.example.homework2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TokenCheck tokenCheck;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto saveComment(Long id, CommentRequsetDto commentRequsetDto, HttpServletRequest request) {
        User user = tokenCheck.tokenChecking(request);
        Post post = postRepository.findByIdAndUsername(id, user.getUsername());
        Comment comment = new Comment(commentRequsetDto,  post, user.getUsername());
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getComment());
    }
}
