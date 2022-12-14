package com.example.homework2.service;

import com.example.homework2.entity.Comment;
import com.example.homework2.entity.CommentLikes;
import com.example.homework2.entity.User;
import com.example.homework2.repository.CommentLikesRepository;
import com.example.homework2.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikesService {

    private final CommentLikesRepository commentLikesRepository;
    private final TokenCheck tokenCheck;
    private final CommentRepository commentRepository;


    @Transactional
    public boolean CommentLikes(Long id, HttpServletRequest httpServletRequest){
        User user = tokenCheck.tokenChecking(httpServletRequest);
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        Optional<CommentLikes> commentLikes = commentLikesRepository.findByUserIdAndCommentId(id, user.getId());
        if(commentLikes.isEmpty()){
            CommentLikes commentLikes1 = new CommentLikes(comment, user, true);
            commentLikesRepository.save(commentLikes1);
            return commentLikes1.isLike();
        }else{
            commentLikes.orElseThrow().setCommentLikes(!commentLikes.get().isLike());
        }
        return commentLikes.get().isLike();
    }
}
