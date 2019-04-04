package com.numb.wfjz.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 处理视图请求的Controller
 * @author Numblgw
 * @date 2018/12/6 13:18
 */
@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequiresRoles(value = {"ADMIN", "SUPER_ADMIN"},logical = Logical.OR)
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequiresRoles("BANNED_USER")
    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
