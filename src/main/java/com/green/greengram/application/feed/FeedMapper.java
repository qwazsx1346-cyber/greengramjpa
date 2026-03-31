package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.FeedDeleteReq;
import com.green.greengram.application.feed.model.FeedGetReq;
import com.green.greengram.application.feed.model.FeedGetRes;
import com.green.greengram.application.feed.model.FeedPostReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedMapper {
    int save(FeedPostReq req); //insert, update, delect는 int또는 void만 적을 수 있다. select빼고는 모두 다 int(영향받은 행 수 밖에 들어갈수없기때문에)
    List<FeedGetRes> findAll(FeedGetReq req);

    int savePics(@Param("feedId") long feedId
                ,@Param("picSavedNames") List<String> picSavedNames); //여러개 보낼때에는 @Param애노테이션을 사용해줘야함. 구분을 못해서 얘는 이름이다 라는 표시를 위함.
    List<String> findPicsById(long feedId);
    int delete(FeedDeleteReq req);
    int deleteRef(FeedDeleteReq req);
}
