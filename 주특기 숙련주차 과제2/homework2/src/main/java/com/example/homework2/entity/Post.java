package com.example.homework2.entity;

import com.example.homework2.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column(nullable = false)
    private String username;
    @Column
    private String content;

    public Post(PostRequestDto requestDto, String username){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
    }
    public Post(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
