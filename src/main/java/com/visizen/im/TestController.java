package com.visizen.im;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
public class TestController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
