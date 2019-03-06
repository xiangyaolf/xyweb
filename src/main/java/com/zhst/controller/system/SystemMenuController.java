package com.zhst.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理
 */
@RequestMapping(value = "/system/menu")
@Controller
public class SystemMenuController {
    
    @RequestMapping(value = "/list")
    public String list() {
        return "system/menu/list";
    }
}
