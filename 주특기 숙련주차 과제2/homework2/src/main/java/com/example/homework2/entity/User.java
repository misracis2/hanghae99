package com.example.homework2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, columnDefinition = "varchar(10)")
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    List<PostLikes> postLikesList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<CommentLikes> commentLikesList = new ArrayList<>();

    public User(String username, String password, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
