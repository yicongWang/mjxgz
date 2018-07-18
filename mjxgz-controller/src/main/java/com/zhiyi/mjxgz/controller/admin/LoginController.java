package com.zhiyi.mjxgz.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @RequestMapping(value ="/")
    public String go(){ 
        return "pages-login";
    }
    
    @RequestMapping(value = "/login")
    public String login(String account,String pw){ 
        return "index";
    }
}