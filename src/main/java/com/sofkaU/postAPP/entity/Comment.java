package com.sofkaU.postAPP.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Comment")
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Long fkPostId;

    private Long fkUserId;
}
