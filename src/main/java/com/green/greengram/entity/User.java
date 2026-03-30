package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity //테이블을 만든다.
@Getter
@Setter
public class User extends CreatedUpdatedAt {
    @Id //대리키. 이 애노테이션 붙이면 PK가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id; //모든테이블에 있어야하는것? > PK

    @Column(nullable = false, length = 20, unique = true)
    private String uid; //DB에서 varchar와 같은 타입 -> string

    @Column(nullable = false, length = 100)
    private String upw;

    @Column(nullable = false, length = 10)
    private String nm;

    @Column(nullable = true, length = 50) //nullable에 default값이 true라 안적어도되지만 이해를위해 적음
    private String pic;
}
