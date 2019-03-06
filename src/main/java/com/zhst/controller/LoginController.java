package com.zhst.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @RequestMapping(value = "/login/page")
    public String loginPage() {
        return "login/page";
    }

    //登录入口
    @RequestMapping(value = "/index")
    public String login() {
        //认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //加载
        System.out.println(username + "_");
        return "index";
    }

}
