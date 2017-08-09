package com.visizen.im.user.dao;

import com.visizen.im.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    void add(User user) throws Exception;

    User findByUsername(String username);
}
