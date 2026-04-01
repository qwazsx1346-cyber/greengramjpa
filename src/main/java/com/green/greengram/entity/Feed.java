package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    //내가 one이고 feedPic이 many // mappedBy 이름은 feedPic에 23번줄에있는 변수명을 적어주면 됨
    //참조 당하는 쪽은 항상 One, mappedBy는 상대방쪽에서 나를 참조하는 필드명
    //cascade All은 Feed가 삭제되면 참조하는 쪽도 삭제처리
    //orphanRemoval은 자식이 고아가 되면 해당 자식은 삭제처리, 즉, pics에서 특정 객체 삭제하면 그 객체와 맵핑되어있는 row가 삭제
    //사진
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedPic> pics = new ArrayList<>();

    //댓글
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedComment> comments = new ArrayList<>();

    //좋아요
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedLike> feedLikes = new ArrayList<>();
}
