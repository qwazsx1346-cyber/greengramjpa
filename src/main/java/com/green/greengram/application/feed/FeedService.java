package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.*;
import com.green.greengram.configuration.util.ImgUploadManager;
import com.green.greengram.configuration.util.MyFileUtil;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedPic;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository; //DI, final과 위에 RequiredArgsConstructor를 붙여서
                                                //생성자로 주입받고 있는상태
    private final FeedPicRepository feedPicRepository;
    private final FeedMapper feedMapper;
    private final ImgUploadManager imgUploadManager;
    private final MyFileUtil myFileUtil;

    @Transactional // 27번과 36번라인 둘 다 성공해야 작업됨. 하나라도 실패하면 DB에 등록이안됨
    public FeedPostRes postFeed(FeedPostReq req, List<MultipartFile> pics) {
        User signedUser = new User();
        signedUser.setId(req.getSignedUserId());

        Feed newFeed = new Feed();
        newFeed.setContents( req.getContents() );
        newFeed.setLocation( req.getLocation() );
        newFeed.setWriterUser( signedUser ); //넣는방법이 좀 까다로워서 다시 확인하기
        //이 시점까지는 newFeed는 영속성이 없음

        feedRepository.save(newFeed);
        //newFeed는 영속성이 생긴다.

        //int saveAffectedRows = feedMapper.save(req);

        //save 이후에 방금 insert한 feed테이블의 id값이 필요해요.
//        long feedId = req.getFeedId();
        long feedId = newFeed.getId();
        log.info("feedId: {}", feedId);

        //saveFeedPics메소드 호출하고 싶다!!!!!!!!!!!!!!!!!!!!!!!
        List<String> picSavedNames = imgUploadManager.saveFeedPics(feedId, pics);

        try{
            //feedMapper.savePics(feedId, picSavedNames);

            for (String pic : picSavedNames) { //pic에 picSavedNames을 하나씩 담는다.향상된 for문
                FeedPic newFeedPic = new FeedPic(newFeed, pic);
                feedPicRepository.save(newFeedPic);
            }

        } catch (Exception e) {
            //에러가 발생하면 사진(파일)을 지운다.
            String directoryPath = String.format("%s/feed/%d", myFileUtil.fileUploadPath, feedId);
            myFileUtil.deleteDirectory(directoryPath);
            throw e;
        }

        return new FeedPostRes(feedId, picSavedNames);
    }

    //여기서 n+1 이슈 발생
    public List<FeedGetRes> getFeedList(FeedGetReq req) {
        List<FeedGetRes> list = feedMapper.findAll(req);
        //작업!!피드 당 사진 정보를 가져오는 작업을 해야한다.
        for (FeedGetRes res : list) {
            //사진 가져오는 select
            List<String> pics = feedMapper.findPicsById(res.getId());
            res.setPics(pics);
        }
        return list;
    }

    //n+1이슈 해결방법
    public List<FeedGetRes> getFeedList2(FeedGetReq req) {
        return feedMapper.findAllResultMap(req);
    }

    @Transactional
    public int deleteFeed(FeedDeleteReq req) {

//        //feed_pic, feed_like, feed_comment에 feedId가 사용된(참조된) 모든 row를 삭제
//        feedMapper.deleteRef(req);
//
//        //feed 테이블의 row는 가장 마지막에 삭제처리
//        feedMapper.delete(req);

        User signedUser = new User();
        signedUser.setId(req.getSignedUserId());
        Feed feedForDel = feedRepository.findByIdAndWriterUser( req.getFeedId() , signedUser );
        feedRepository.delete(feedForDel);

        //폴더 째 삭제
        String delDirectoryPath = String.format("%s/feed/%d",myFileUtil.fileUploadPath, req.getFeedId());
        myFileUtil.deleteDirectory(delDirectoryPath);
        return 1;
    }
}
