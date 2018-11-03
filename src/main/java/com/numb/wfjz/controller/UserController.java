package com.numb.wfjz.controller;

import com.numb.wfjz.pojo.User;
import com.numb.wfjz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 处理用户请求通用的Controller
 * 关于请求方式：http 1.1 之后有了8种method 其中有  post delete put get 分别对应增删改查
 * 获取数据使用get 插入或者更新数据用post或者put  删除数据用delete
 * restful 注意，url中尽量不要出现动词
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转至登录页面
     * @return  登录页面逻辑名
     */
    @GetMapping("/loginView")
    public String toLoginView(){
        return "login";
    }

    /**
     * 接收登录提交的数据
     * @return  主页面逻辑名
     */
    @ResponseBody
    @PostMapping("/login")
    public User login(@RequestBody User user){

        return user;
    }
}
