package com.example.homework2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class PostLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    boolean isLike = false;

    public PostLikes(Long postId, User user, boolean isLike){
        this.postId = postId;
        this.user = user;
        this.isLike = isLike;
    }

    public void setPostLikes(boolean isLike){
        this.isLike = isLike;
    }
}
