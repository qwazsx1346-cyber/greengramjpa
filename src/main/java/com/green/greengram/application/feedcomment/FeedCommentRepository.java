package com.green.greengram.application.feedcomment;

import com.green.greengram.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;

//<어떤 엔터티랑 연결하냐, 그 엔터티의 PK타입이 무엇이냐?>
public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
}
