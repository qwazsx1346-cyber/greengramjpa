package com.green.greengram.application.feedcomment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor //생성자가 있어야해서 붙여줌. 아래 7개를 받는 생성자가 만들어짐.
public class FeedCommentGetRes {
    @JsonProperty("feedCommentId")
    private long id;
    private long feedId;
    private String comment;
    private long writerUserId;
    @JsonProperty("writerNickName")
    private String writerNm;
    private String writerPic;
    private LocalDateTime createdAt;
}
