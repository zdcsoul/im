package com.visizen.im.user.web;

import com.visizen.im.user.entity.User;
import com.visizen.im.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @RequestMapping(value="/likeUsername",method= RequestMethod.GET)
    public List<User> likeUsername(String username){
        return userService.likeUsername(username);
    }
}
