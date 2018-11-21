package com.numb.wfjz.controller;

import com.numb.wfjz.common.util.ResponseJson;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.service.UserService;
import org.apache.ibatis.annotations.Param;
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
     * 用户注册与后台添加用户通用，验证用户名是否已存在
     * @param username  用户名
     * @return  Response<Boolean>存在则 data 为 true，不存在则 data 为 false
     */
    @ResponseBody
    @GetMapping("/usernameRepeat")
    public ResponseJson<Boolean> checkUserRepeat(@Param("username") String username){
        return ResponseJson.success(userService.checkUsernameRepeat(username));
    }
}
