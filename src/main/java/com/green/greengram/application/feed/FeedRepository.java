package com.green.greengram.application.feed;

import com.green.greengram.entity.Feed;
import com.green.greengram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//<어떤 엔터티랑 연결하냐, 그 엔터티의 PK타입이 무엇이냐?>
public interface FeedRepository extends JpaRepository<Feed, Long> {
    //쿼리메소드로 id, user_id를 where 조건으로 해서 1개의 row를 가져오고 싶다.
    //select * from Feed where
    Feed findByIdAndWriterUser(Long id, User signedUser);
}
