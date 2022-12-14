package com.example.homework2.entity;

import com.example.homework2.dto.CommentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    List<CommentLikes> commentLikesList = new ArrayList<>();

    public Comment(CommentRequestDto commentRequestDto, Post post, User user){
        this.content = commentRequestDto.getComment();
        this.post = post;
        this.user = user;
    }

    public void update(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getComment();
    }

}
