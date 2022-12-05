package com.example.homework1.entity;

import com.example.homework1.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private String username;
    @Column
    private String content;

    public Post(PostRequestDto requestDto, String username){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
    }
}
