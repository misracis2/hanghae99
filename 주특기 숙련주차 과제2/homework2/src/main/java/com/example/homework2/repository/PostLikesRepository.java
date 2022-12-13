package com.example.homework2.repository;

import com.example.homework2.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    Optional<PostLikes> findByUserIdAndPostId(Long userId, Long postId);


}
