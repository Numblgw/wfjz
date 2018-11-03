package com.numb.wfjz.controller;

import com.numb.wfjz.common.util.ResponseJson;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 跳转至管理页面
     * @return  管理页面逻辑名
     */
    @GetMapping("/adminView")
    public String toAdminView(){
        return "admin";
    }

    /**
     * 以json格式，为后台管理页面返回用户列表
     * @return  ArrayList<User>类型的ResponseJson对象
     */
    @ResponseBody
    @PostMapping("/userList")
    public ResponseJson<User> getUserList(){
        System.out.println("111111*************************************************************");
        return ResponseJson.success(adminService.getUserList());
    }
}
