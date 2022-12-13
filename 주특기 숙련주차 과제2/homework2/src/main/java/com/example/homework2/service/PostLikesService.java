package com.example.homework2.service;

import com.example.homework2.entity.PostLikes;
import com.example.homework2.entity.User;
import com.example.homework2.repository.PostLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikesService {

    private final TokenCheck tokenCheck;
    private final PostLikesRepository postLikesRepository;

    @Transactional
    public boolean PostLikes(Long id, HttpServletRequest httpServletRequest){
        User user = tokenCheck.tokenChecking(httpServletRequest);
        Optional<PostLikes> postLikes = postLikesRepository.findByUserIdAndPostId(id, user.getId());
        if(postLikes.isEmpty()){
            PostLikes postLikes1 = new PostLikes(id, user, true);
            postLikesRepository.save(postLikes1);
            return postLikes1.isLike();
        }else if(postLikes.get().isLike()){
            postLikes.orElseThrow().setPostLikes(false);
        }else if(!postLikes.get().isLike()){
            postLikes.orElseThrow().setPostLikes(true);
        }
        return postLikes.get().isLike();
    }

}
