package com.numb.wfjz.controller;

import com.numb.wfjz.pojo.User;
import com.numb.wfjz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转至登录页面
     * @return  登录页面逻辑名
     */
    @RequestMapping("/loginView")
    public String toLoginView(){
        return "login";
    }

    /**
     * 接收登录提交的数据
     * @return  主页面逻辑名
     */
    @ResponseBody
    @RequestMapping("/login")
    public User login(@RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        user.setUsername("000000");
        user.setPassword("111111");
        return user;
    }

    @RequestMapping("/insert")
    public String insertUser(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println("###############################################################");
        System.out.println(userService.insertOne(user));
        return "success";
    }
}
