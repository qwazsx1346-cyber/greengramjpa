package com.green.greengram.application.feedcomment.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FeedCommentGetReq {
    @Positive(message = "page는 1이상 값을 보내야 합니다.")
    private int page;
    @Positive
    private long feedId;
    private int startIdx;
    @Min(value = 20, message = "size는 20이상 값을 보내야 합니다.")
    private int size;

    public FeedCommentGetReq(int page, long feedId, int size) {
        this.page = page;
        this.feedId = feedId;
        this.size = size;
        this.startIdx = (page - 1) * size;
    }
}
