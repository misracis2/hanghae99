package com.example.homework2.repository;

import com.example.homework2.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    Optional<CommentLikes> findByUserIdAndCommentId(Long userId, Long commentId);
}
