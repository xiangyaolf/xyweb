package com.zhst.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理
 */
@RequestMapping(value = "/system/user")
@Controller
public class SystemUserController {

    @RequestMapping(value = "/list")
    public String list() {
        return "system/user/list";
    }
}
