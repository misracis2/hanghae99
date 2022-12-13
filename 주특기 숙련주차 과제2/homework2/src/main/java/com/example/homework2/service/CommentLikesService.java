package com.example.homework2.service;

import com.example.homework2.entity.CommentLikes;
import com.example.homework2.entity.User;
import com.example.homework2.repository.CommentLikesRepository;
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


    @Transactional
    public boolean CommentLikes(Long id, HttpServletRequest httpServletRequest){
        User user = tokenCheck.tokenChecking(httpServletRequest);
        Optional<CommentLikes> commentLikes = commentLikesRepository.findByUserIdAndCommentId(id, user.getId());
        if(commentLikes.isEmpty()){
            CommentLikes commentLikes1 = new CommentLikes(id, user, true);
            commentLikesRepository.save(commentLikes1);
            return commentLikes1.isLike();
        }else if(commentLikes.get().isLike()){
            commentLikes.orElseThrow().setCommentLikes(false);
        }else if(!commentLikes.get().isLike()){
            commentLikes.orElseThrow().setCommentLikes(true);
        }
        return commentLikes.get().isLike();
    }
}
