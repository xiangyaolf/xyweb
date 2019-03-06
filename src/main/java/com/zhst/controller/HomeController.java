package com.zhst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页访问控制器
 */
@Controller
public class HomeController {

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }
}
