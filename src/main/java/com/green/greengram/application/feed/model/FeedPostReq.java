package com.green.greengram.application.feed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FeedPostReq {
    private String location;
    private String contents;
    private long signedUserId;
    private long feedId;
    // private long signedUserId; 가능하면 프론트에서 받는데이터만 적어주는것이 좋음

    //Setter있으면 안넣어도 됨
    //public void setSignedUserId(long signedUserId) {this.signedUserId = signedUserId;}

}
