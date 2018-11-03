package com.numb.wfjz.service;

import com.numb.wfjz.pojo.User;

import java.util.List;

public interface AdminService {
    /**
     * 获取用户列表
     * @return  User对象的list集合
     */
    List<User> getUserList();
}
