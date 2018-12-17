package com.numb.wfjz.service;

import com.github.pagehelper.PageInfo;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserDetail;
import com.numb.wfjz.pojo.UserRole;

import java.util.List;

/**
 * @ClassName UserService
 * @Description 用户相关业务处理接口
 * @Author Numblgw
 * @Date 2018/11/1 13:52
 */
public interface UserService {

    /**
     * ShiroRealm调用的认证方法，通过username查询用户
     * @param username  用户名
     * @return  User对象或者null
     */
    User login(String username);

    /**
     * 获取用户具有的角色
     * @param id    用户id
     * @return  用户角色列表
     */
    UserRole getUserRoleById(int id);
    /**
     * 查询用户名是否已存在
     * @param username  用户名
     * @return  存在true 不存在false
     */
    boolean checkUsernameRepeat(String username);
    /**
     * 获取用户列表,并且使用分页插件分页
     * @return  PageInfo对象
     */
    PageInfo getUserListByPage(int pageNum, int pageSize);

    /**
     * 新增用户和用户详细信息
     * @param userDetail    用户详细信息
     * @return     是否新增成功
     */
    boolean addUserDetail(UserDetail userDetail);

    /**
     * 修改用户详细信息
     * @param userDetail    用户详细信息
     * @return      是否修改成功
     */
    boolean updateUserDetail(UserDetail userDetail);

    /**
     * 通过用户名或者id查询用户信息
     * @param user  用户pojo类
     * @return  用户详细信息pojo类
     */
    UserDetail getUserInfo(User user);

    /**
     * 通过id列表批量删除用户
     * @param idList    id列表
     * @return  是否删除成功
     */
    boolean deleteUserByIdList(List<Integer> idList);
}
