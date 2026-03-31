package com.green.greengram.application.feed;

import com.green.greengram.entity.FeedPic;
import org.springframework.data.jpa.repository.JpaRepository;

//<어떤 엔터티랑 연결하냐, 그 엔터티의 PK타입이 무엇이냐?>
public interface FeedPicRepository extends JpaRepository<FeedPic, Long> {
    //쿼리메소드, 메소드 이름으로 쿼리문을 만든다.
}
