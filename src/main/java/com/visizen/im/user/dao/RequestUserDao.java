package com.visizen.im.user.dao;

import com.visizen.im.user.entity.RequestUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
@Mapper
public interface RequestUserDao {
    /**
     * 添加一个请求
     * @param requestUser
     * @throws Exception
     */
    void add(RequestUser requestUser) throws Exception;

    /**
     * 查询未读消息
     * @param userId
     * @return
     */
    List<RequestUser> findUnReadMessage(Long userId);

    /**
     * 更新请求
     * @param requestUser
     * @throws Exception
     */
    void update(RequestUser requestUser) throws Exception;
}
