package com.numb.wfjz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.numb.wfjz.mapper.AdminMapper;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserDetail;
import com.numb.wfjz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    @Transactional
    public boolean addUserDetail(UserDetail userDetail) {
        boolean flag = false;
        try {
            userDetail.setGmtCreate(new Date());
            if (adminMapper.insertUser(userDetail) == 1 && adminMapper.insertUserDetail(userDetail) == 1) {
                flag = true;
            }
        } catch(Exception e){
            //打印日志
            throw new RuntimeException();
        } finally {
            return flag;
        }
    }

    @Override
    public boolean updateUserDetail(UserDetail userDetail) {
        userDetail.setGmtModified(new Date());
        //如果只有id没有用户名，则直接通过id修改
        if(userDetail.getId() != null && userDetail.getUsername() == null){
            return adminMapper.updateUserDetail(userDetail) == 1;
        }
        //只有用户名或者id和用户名都有的时，需要先查询表中用户名对应的id
        int id = adminMapper.selectUserByUsername(userDetail.getUsername());
        if(null == userDetail.getId() || Objects.equals(id,userDetail.getId())){
            userDetail.setId(id);
            return adminMapper.updateUserDetail(userDetail) == 1;
        }
        return false;
    }

    @Override
    public UserDetail getUserInfo(User user) {
        if(user.getId() != null){
            //有id则直接通过id查询
            return adminMapper.selectUserDetailById(user.getId());
        }
        if(user.getId() == null && user.getUsername() != null){
            //如果没有id则先查出id在通过id进行查询
            return adminMapper.selectUserDetailById(adminMapper.selectUserByUsername(user.getUsername()));
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteUserByIdList(List<Integer> idList) {
        boolean flag = false;
        try{
            if(adminMapper.deleteUserByIdList(idList) != 0 && adminMapper.deleteUserDetailByIdList(idList) != 0) {
                flag = true;
            }
        } catch (Exception e){
            //打印日志
            throw new RuntimeException();
        } finally {
            return flag;
        }
    }
}
