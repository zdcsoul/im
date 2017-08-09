package com.visizen.im.user.service;

import com.visizen.im.user.entity.User;
import com.visizen.im.user.utils.ResultVO;

/**
 * Created by Administrator on 2017/8/9.
 */
public interface RequestUserService {

    ResultVO request(User requestUser,String username) throws Exception;
}
