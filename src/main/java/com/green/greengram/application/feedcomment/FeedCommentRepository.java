package com.green.greengram.application.feedcomment;

import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

//<어떤 엔터티랑 연결하냐, 그 엔터티의 PK타입이 무엇이냐?>
//from 에는 테이블명이 아닌 엔터티 이름을 적어줌, 한줄 적을때마다 뒤에 빈칸을 꼭 넣어주어야함
//FeedCommentGetRes에 createdAt 타입을 LocalDateTime으로 바꿔줘야 빨간줄이 안뜸
//FeedCommentGetRes에 적인 순서대로 적어주어야 함.
public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
    @Query(" SELECT new com.green.greengram.application.feedcomment.model.FeedCommentGetRes(fc.id, fc.feed.id, fc.comment, u.id, u.nm, u.pic, fc.createdAt) " +
            " FROM FeedComment fc JOIN fc.user u " +
            " WHERE fc.feed.id = :feedId " +
            " ORDER BY fc.feed.id DESC")
    List<FeedCommentGetRes> getFeedCommentList(@Param("feedId") Long feedId, Pageable pageable);
}
