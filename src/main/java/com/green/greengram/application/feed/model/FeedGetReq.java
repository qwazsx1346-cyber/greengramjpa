package com.green.greengram.application.feed.model;

import lombok.Getter;
import lombok.ToString;

//page=1&size=20
@Getter
@ToString
public class FeedGetReq {
    private int page;
    private int size;
    private Long profileUserId; //long말고 Long은 똑같은 타입이지만 null값이 허용됨.
    private long signedUserId;
    private int startIdx;

    public FeedGetReq(int page, int size, Long profileUserId) {
        this.page = page;
        this.size = size;
        this.startIdx = ( page - 1 ) * size;
        this.profileUserId = profileUserId;
    }

    public void setSignedUserId(long signedUserId) {
        this.signedUserId = signedUserId;
    }
}
