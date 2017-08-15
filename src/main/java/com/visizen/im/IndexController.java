package com.visizen.im;

import com.visizen.im.config.ChatWebSocketHandler;
import com.visizen.im.user.entity.User;
import com.visizen.im.user.service.RequestUserService;
import com.visizen.im.user.service.UserService;
import com.visizen.im.user.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
public class IndexController {

    @Autowired private RequestUserService requestUserService;

    @Autowired private UserService userService;

    @RequestMapping("chat")
    public String index(HttpSession session, Model model){
        User currentUser = (User)session.getAttribute("user");
        List<User> friends = userService.findMyFrends(currentUser.getUserId());
        List<Long> ids = new ArrayList<>();
        for(User user : friends){
            ids.add(user.getUserId());
        }
        List<Long> longs = ChatWebSocketHandler.getMyFrendCount(ids);
        for(User user : friends){
            for(Long id : longs){
                if(id.longValue() == user.getUserId().longValue()){
                    user.setOnline(User.ONLINE_Y);
                    break;
                }
            }
            ids.add(user.getUserId());
        }
        Collections.sort(friends, (o1, o2) -> o1.getOnline() < o2.getOnline() ? 1 : -1);
        model.addAttribute("myFriends",friends);
        model.addAttribute("friendsCount","(" + longs.size() + "/" + ids.size() + ")");
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
