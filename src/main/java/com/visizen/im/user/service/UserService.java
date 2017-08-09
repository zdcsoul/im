package com.visizen.im.user.service;

import com.visizen.im.user.entity.User;
import com.visizen.im.user.utils.ResultVO;

public interface UserService {

    ResultVO regist(User user) throws Exception;
}
