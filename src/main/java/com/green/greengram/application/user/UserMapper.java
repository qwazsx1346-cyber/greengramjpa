package com.green.greengram.application.user;

import com.green.greengram.application.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //JPA에서 해결하고있어서 지워도 됨
//    int signUp(UserSignUpReq req);
//    UserGetOneRes findByUid(String uid);
//    UserGetOneRes findById(long id);
//    int updUser(UserUpdDto dto);
    UserProfileGetRes findProfileUser(UserProfileGetReq req);
}
