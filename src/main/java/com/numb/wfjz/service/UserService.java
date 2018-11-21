package com.numb.wfjz.service;

import com.numb.wfjz.pojo.User;

public interface UserService {

    /**
     * 通过id查询用户
     * @param id    用户唯一标识
     * @return      用户pojo
     */
    User findUserById(int id);

    /**
     * 插入一条用户信息
     * @param user  封装username与password的user对象
     * @return      是否插入成功
     */
    boolean insertOne(User user);

    /**
     * 查询用户名是否已存在
     * @param username  用户名
     * @return  存在true 不存在false
     */
    boolean checkUsernameRepeat(String username);
}
