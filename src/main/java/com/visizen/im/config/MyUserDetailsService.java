package com.visizen.im.config;


import com.visizen.im.user.dao.UserDao;
import com.visizen.im.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/2/6.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
