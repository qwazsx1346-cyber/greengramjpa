package com.green.greengram.application.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileGetReq {
    private long profileUserId; //프로일 사용자 userId
    private long signedUserId; //로그인한 사용자 userId
}
