package com.example.homework2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class CommentLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long commentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column
    boolean isLike;

    public CommentLikes(Long commentId, User user, boolean isLike) {
        this.commentId = commentId;
        this.user = user;
        this.isLike = isLike;
    }


    public void setCommentLikes(boolean isLike) {
        this.isLike = isLike;
    }
}
