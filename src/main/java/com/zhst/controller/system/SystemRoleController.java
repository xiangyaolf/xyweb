package com.zhst.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理
 */
@RequestMapping(value = "/system/role")
@Controller
public class SystemRoleController {
    
    @RequestMapping(value = "/list")
    public String list() {
        return "system/role/list";
    }
}
