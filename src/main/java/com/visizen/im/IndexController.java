package com.visizen.im;

import com.visizen.im.user.entity.User;
import com.visizen.im.user.service.RequestUserService;
import com.visizen.im.user.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
public class IndexController {

    @Autowired private RequestUserService requestUserService;

    @RequestMapping("chat")
    public String index(){
        return "chat";
    }

    @RequestMapping(value="login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/requestUser",method=RequestMethod.POST)
    @ResponseBody
    public ResultVO requestUser(String username, HttpSession session){
        User currentUser = (User)session.getAttribute("user");
        ResultVO resultVO = new ResultVO();
        try {
            resultVO = requestUserService.request(currentUser,username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVO;
    }
}
