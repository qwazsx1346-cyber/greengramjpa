package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //테이블을 만든다.
@Getter
@Setter
@ToString
public class FeedComment extends CreatedUpdatedAt{
    @Id //대리키. 이 애노테이션 붙이면 PK가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id; //모든테이블에 있어야하는것? > PK

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false, length = 1_000)
    private String comment;
}
