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

import java.util.List;

/**
 *
 */
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

    /**
     * 接收id或者username查询用户信息
     * @param id    用户id
     * @param username  用户名
     * @return  成功返回封装用户信息的ResponseJson对象，失败返回封装了错误码和错误信息的ResponseJson的对象
     */
    @ResponseBody
    @GetMapping("/userInfo")
    public ResponseJson getUserInfo(@Param("id") int id , @Param("username") String username){
        User user = new User();
        user.setId(id == 0 ? null : id);
        user.setUsername(username);
        UserDetail userDetail = adminService.getUserInfo(user);
        return null == userDetail ? ResponseJson.fail(ResponseEnum.SELECT_ERROR) : ResponseJson.success(userDetail);
    }

    /**
     * 接收id数组，批量删除用户
     * @param idList    id集合
     * @return  封装状态码以及信息的ResponseJson对象
     */
    @ResponseBody
    @DeleteMapping("/deleteUser")
    public ResponseJson deleteUser(@RequestBody List<Integer> idList){
        if(adminService.deleteUserByIdList(idList)){
            return ResponseJson.success();
        }
        return ResponseJson.fail(ResponseEnum.DELETE_ERROR);
    }
}
