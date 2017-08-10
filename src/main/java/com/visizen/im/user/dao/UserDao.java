package com.visizen.im.user.dao;

import com.visizen.im.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    void add(User user) throws Exception;

    User findByUsername(String username);

    List<User> likeUsername(String username);

    List<User> findMyFrends(Long userId);
}
