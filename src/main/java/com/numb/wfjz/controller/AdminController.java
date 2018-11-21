package com.numb.wfjz.controller;

import com.numb.wfjz.common.enums.ResponseEnum;
import com.numb.wfjz.common.util.ResponseJson;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserDetail;
import com.numb.wfjz.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/userList")
    public ResponseJson<User> getUserList(@RequestParam Integer page,@RequestParam Integer limit){
        return ResponseJson.success(adminService.getUserListByPage(page,limit));
    }

    /**
     * 新增用户信息
     * @param userDetail    用户详细信息pojo
     * @return  成功返回状态码200  失败返回相应的错误码和msg
     */
    @ResponseBody
    @PostMapping("/addUser")
    public ResponseJson addUser(@RequestBody UserDetail userDetail){
        if(adminService.addUserDetail(userDetail)){
            return ResponseJson.success();
        }
        return ResponseJson.fail(ResponseEnum.INSERT_ERROR);
    }

    /**
     * 修改用户信息
     * @param userDetail    用户详细信息pojo
     * @return  成功返回状态码200  失败返回相应的错误码和msg
     */
    @ResponseBody
    @PutMapping("/updateUser")
    public ResponseJson updateUser(@RequestBody UserDetail userDetail){
        if(adminService.updateUserDetail(userDetail)){
            return ResponseJson.success();
        }
        return ResponseJson.fail(ResponseEnum.UPDATE_ERROR);
    }

    @ResponseBody
    @GetMapping("/userInfo")
    public ResponseJson getUserInfo(@Param("id") int id , @Param("username") String username){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        UserDetail userDetail = adminService.getUserInfo(user);
        return null == userDetail ? ResponseJson.fail(ResponseEnum.SELECT_ERROR) : ResponseJson.success(userDetail);
    }
}
