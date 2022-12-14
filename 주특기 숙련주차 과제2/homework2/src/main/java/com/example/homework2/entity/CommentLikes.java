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

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column
    boolean isLike;

    public CommentLikes(Comment comment, User user, boolean isLike) {
        this.comment = comment;
        this.user = user;
        this.isLike = isLike;
    }


    public void setCommentLikes(boolean isLike) {
        this.isLike = isLike;
    }
}
