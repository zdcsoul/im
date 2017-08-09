package com.visizen.im;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
public class IndexController {

    @RequestMapping("chat")
    public String index(){
        return "chat";
    }

    @RequestMapping(value="login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }
}
