package com.visizen.im.user.service;

import com.visizen.im.user.entity.User;
import com.visizen.im.user.utils.ResultVO;

import java.util.List;

public interface UserService {

    ResultVO regist(User user) throws Exception;

    List<User> likeUsername(String username);
}
