package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter //수정은 없으니 생성자로 처리,Setter없이
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_feed_feedLike", columnNames = {"feed_id", "pic"})
    }
)
public class FeedPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @Column(length = 50, nullable = false)
    private String pic;

    public FeedPic(Feed feed, String pic) {
      this.feed = feed;
      this.pic = pic;
    }
}
