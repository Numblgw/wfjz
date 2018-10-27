package com.numb.wfjz.service.impl;

import com.numb.wfjz.mapper.UserMapper;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(int id){
        return userMapper.findById(id);
    }

    @Override
    public boolean insertOne(User user) {
        return userMapper.insertOne(user) == 1;
    }
}
