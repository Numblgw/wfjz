package com.numb.wfjz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.numb.wfjz.mapper.AdminMapper;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserDetail;
import com.numb.wfjz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageInfo getUserListByPage(int pageNum , int pageSize) {
        //使用PageHelper分页插件
        PageHelper.startPage(pageNum,pageSize);
        //紧跟的第一个select方法会被分页
        List<UserDetail> list = adminMapper.selectUserDetailList();
        //使用PageInfo对象进行封装并返回
        return new PageInfo(list);
    }
}
