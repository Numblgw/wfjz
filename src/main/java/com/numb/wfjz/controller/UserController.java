package com.numb.wfjz.controller;

import com.numb.wfjz.common.enums.ResponseEnum;
import com.numb.wfjz.common.util.ResponseJson;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserDetail;
import com.numb.wfjz.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;

/**
 * @ClassName UserController
 * @Description 用户相关请求通用处理器
 * @Author Numblgw
 * @Date 2018/12/6 13:52
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user  pojo
     * @return  ResponseJson
     */
    @ResponseBody
    @PostMapping("/login")
    public ResponseJson login(@RequestBody User user){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {
            return ResponseJson.fail(ResponseEnum.UNKNOWN_ACCOUNT);
        } catch (IncorrectCredentialsException e2) {
            return ResponseJson.fail(ResponseEnum.INCORRECT_CREDENTIALS);
        } catch (Exception e3) {
            return ResponseJson.fail(ResponseEnum.LOGIN_FAILURE);
        }
        return ResponseJson.success();
    }

    /**
     * TODO   暂未实现，待后续添加邮箱或者手机短信验证注册，目前注册与后台addUser逻辑相同
     * @param userDetail
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public ResponseJson register(@RequestBody UserDetail userDetail){
        if(userService.addUserDetail(userDetail)){
            return ResponseJson.success();
        }
        return ResponseJson.fail(ResponseEnum.INSERT_ERROR);
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

    /**
     * 以json格式，为后台管理页面返回用户列表
     * @return  ArrayList<User>类型的ResponseJson对象
     */
    @ResponseBody
    @GetMapping("/userList")
    public ResponseJson<User> getUserList(@RequestParam Integer page,@RequestParam Integer limit){
        return ResponseJson.success(userService.getUserListByPage(page,limit));
    }

    /**
     * 新增用户信息
     * @param userDetail    用户详细信息pojo
     * @return  成功返回状态码200  失败返回相应的错误码和msg
     */
    @ResponseBody
    @PostMapping("/addUser")
    public ResponseJson addUser(@RequestBody UserDetail userDetail){
        if(userService.addUserDetail(userDetail)){
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
        if(userService.updateUserDetail(userDetail)){
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
        UserDetail userDetail = userService.getUserInfo(user);
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
        if(userService.deleteUserByIdList(idList)){
            return ResponseJson.success();
        }
        return ResponseJson.fail(ResponseEnum.DELETE_ERROR);
    }
}
