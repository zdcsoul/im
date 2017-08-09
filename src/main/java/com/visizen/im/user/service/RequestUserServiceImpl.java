package com.visizen.im.user.service;

import com.visizen.im.user.dao.RequestUserDao;
import com.visizen.im.user.dao.UserDao;
import com.visizen.im.user.entity.RequestUser;
import com.visizen.im.user.entity.User;
import com.visizen.im.user.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/9.
 */
@Service
public class RequestUserServiceImpl implements RequestUserService {

    @Autowired private RequestUserDao requestUserDao;

    @Autowired private UserDao userDao;

    @Override
    @Transactional
    public ResultVO request(User requestUser,String username) throws Exception {
        ResultVO resultVO = new ResultVO();
        User user = userDao.findByUsername(username);
        if(user == null){
            resultVO.setStatus("fail");
            resultVO.setMessage("该用户不存在");
        }else if(user.getUsername().equals(requestUser.getUsername())){
            resultVO.setStatus("fail");
            resultVO.setMessage("不能添加自己为好友");
        }else{
            RequestUser ru = new RequestUser();
            ru.setFromUser(requestUser);
            ru.setToUser(user);
            ru.setFromStatus(1);
            ru.setToStatus(0);
            ru.setCreateTime(new Date());
            requestUserDao.add(ru);
            resultVO.setStatus("success");
            resultVO.setMessage("请求已发送");
        }
        return resultVO;
    }
}
