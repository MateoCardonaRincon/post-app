package com.sofkaU.postAPP.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

}
