package com.example.homework2.entity;

import com.example.homework2.dto.CommentRequsetDto;
import com.example.homework2.dto.PostRequestDto;
import com.example.homework2.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String comment;

    @ManyToOne
    private Post post;

    public Comment(CommentRequsetDto commentRequsetDto, Post post, String username){
        this.comment = commentRequsetDto.getComment();
        this.post = post;
        this.username = username;
    }

    public void update(CommentRequsetDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

}
