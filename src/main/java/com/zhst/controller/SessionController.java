package com.zhst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * session超时控制器
 */
@Controller
public class SessionController {

    /**
     * session timeout
     * @return
     */
    @RequestMapping(value = "/session_timeout")
    public String sessionTimeout() {
        return "session_timeout";
    }
}
