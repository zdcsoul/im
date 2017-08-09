package com.visizen.im.user.service;

import com.visizen.im.user.dao.UserDao;
import com.visizen.im.user.entity.User;
import com.visizen.im.user.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao;

    @Override
    @Transactional
    public ResultVO regist(User user) throws Exception {
        ResultVO resultVO = new ResultVO();
        User euser = userDao.findByUsername(user.getUsername());
        if(euser == null){
            userDao.add(user);
            resultVO.setMessage("注册成功");
            resultVO.setStatus("success");
        }else{
            resultVO.setMessage("用户名已经存在");
            resultVO.setStatus("fail");
        }
        return resultVO;
    }
}
