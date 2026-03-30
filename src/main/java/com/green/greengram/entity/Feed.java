package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //테이블을 만든다.
@Getter
@Setter
@ToString
public class Feed extends CreatedUpdatedAt{
    @Id //대리키. 이 애노테이션 붙이면 PK가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id; //모든테이블에 있어야하는것? > PK

    @Column(nullable = true, length = 50)
    private String location;

    @Column(nullable = true, length = 1_000)
    private String contents;

    @ManyToOne //1대다 관계 표시, 앞에있는게 나 to 상대방
    @JoinColumn(name = "user_id", nullable = false) //User와 외래키 join
    private User writerUser;
}
