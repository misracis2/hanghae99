package com.example.homework2.entity;

import com.example.homework2.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private String username;
    @Column
    private String content;

    @OneToMany
    private List<Comment> commentList;


    public Post(PostRequestDto requestDto, String username){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
    }
    //entity에서 메소드를 가져도 되나?
    public Post(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
