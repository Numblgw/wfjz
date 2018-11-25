package com.numb.wfjz.service;

import com.github.pagehelper.PageInfo;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserDetail;

import java.util.List;

public interface AdminService {
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
