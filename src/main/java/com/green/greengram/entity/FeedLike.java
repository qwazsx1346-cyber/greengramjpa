package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //테이블을 만든다.
@Getter //수정은 없으니 생성자로 처리,Setter없이
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 앞에 public이 아닌 protected주는거
@Table(
    indexes = {
        //복랍인덱스
        //@Index(name = "idx_feed_user", columnList="feed_id, user_id"),
        //인덱스
        //@Index(name = "idx_feed", columnList = "feed_id")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_feed_user", columnNames = {"feed_id", "user_id"})
    }
)

public class FeedLike extends CreatedAt {
    @Id //대리키. 이 애노테이션 붙이면 PK가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id; //모든테이블에 있어야하는것? > PK

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public FeedLike(Feed feed, User user) {
      this.feed = feed;
      this.user = user;
    }
}
