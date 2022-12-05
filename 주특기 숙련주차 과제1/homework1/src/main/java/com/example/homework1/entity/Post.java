package com.example.homework1.entity;

import com.example.homework1.dto.RequestDto;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
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
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime modifiedAt;

    public Post(RequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        //미완성, username을 알아야함

    }
}
