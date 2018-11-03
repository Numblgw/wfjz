package com.numb.wfjz.service.impl;

import com.numb.wfjz.mapper.AdminMapper;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<User> getUserList() {
        return adminMapper.findUserList();
    }
}
