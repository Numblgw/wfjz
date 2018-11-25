package com.numb.wfjz.service;

import com.numb.wfjz.pojo.User;

public interface UserService {
    /**
     * 查询用户名是否已存在
     * @param username  用户名
     * @return  存在true 不存在false
     */
    boolean checkUsernameRepeat(String username);
}
