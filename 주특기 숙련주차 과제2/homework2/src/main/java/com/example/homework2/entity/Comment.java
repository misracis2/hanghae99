package com.example.homework2.entity;

import com.example.homework2.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, Post post, String username){
        this.content = commentRequestDto.getComment();
        this.post = post;
        this.username = username;
    }

    public void update(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getComment();
    }

}
