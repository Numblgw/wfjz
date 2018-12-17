package com.numb.wfjz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.numb.wfjz.common.enums.RoleEnum;
import com.numb.wfjz.common.util.ShiroUtils;
import com.numb.wfjz.mapper.UserMapper;
import com.numb.wfjz.mapper.UserRoleMapper;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserDetail;
import com.numb.wfjz.pojo.UserRole;
import com.numb.wfjz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
/**
 * @ClassName UserServiceImpl
 * @Description 用户相关业务处理
 * @Author Numblgw
 * @Date 2018/11/1 13:52
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User login(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public UserRole getUserRoleById(int id) {
        return userRoleMapper.selectRoleByUserId(id);
    }

    @Override
    public boolean checkUsernameRepeat(String username) {
        return userMapper.countByUsername(username) == 1;
    }

    @Override
    public PageInfo getUserListByPage(int pageNum , int pageSize) {
        //使用PageHelper分页插件
        PageHelper.startPage(pageNum,pageSize);
        //紧跟的第一个select方法会被分页
        List<UserDetail> list = userMapper.selectUserDetailList();
        //使用PageInfo对象进行封装并返回
        return new PageInfo(list);
    }

    @Override
    @Transactional
    public boolean addUserDetail(UserDetail userDetail) {
        boolean flag = false;
        try {
            userDetail.setPassword(ShiroUtils.toMD5(userDetail.getPassword(), userDetail.getUsername()));
            userDetail.setGmtCreate(LocalDateTime.now());
            //插入用户信息和用户详细信息
            if (1 == userMapper.insertUser(userDetail) && 1 == userMapper.insertUserDetail(userDetail)) {
                List<Integer> list = new ArrayList<>();
                list.add(RoleEnum.USER.getCode());
                UserRole userRole = new UserRole(userDetail.getId(), list, LocalDateTime.now());
                //插入用户角色信息（普通用户）
                if(1 == userRoleMapper.insert(userRole)){
                    flag = true;
                }
            }
        } catch(Exception e){
            log.error("addUserDetail method error. msg:" + e.getMessage());
            throw new RuntimeException();
        } finally {
            return flag;
        }
    }

    @Override
    public boolean updateUserDetail(UserDetail userDetail) {
        userDetail.setGmtModified(LocalDateTime.now());
        //如果只有id没有用户名，则直接通过id修改
        if(userDetail.getId() != null && userDetail.getUsername() == null){
            return userMapper.updateUserDetail(userDetail) == 1;
        }
        //只有用户名或者id和用户名都有的时，需要先查询表中用户名对应的id
        int id = userMapper.selectIdByUsername(userDetail.getUsername());
        if(null == userDetail.getId() || Objects.equals(id,userDetail.getId())){
            userDetail.setId(id);
            return userMapper.updateUserDetail(userDetail) == 1;
        }
        return false;
    }

    @Override
    public UserDetail getUserInfo(User user) {
        if(user.getId() != null){
            //有id则直接通过id查询
            return userMapper.selectUserDetailById(user.getId());
        }
        if(user.getId() == null && user.getUsername() != null){
            //如果没有id则先查出id在通过id进行查询
            return userMapper.selectUserDetailById(userMapper.selectIdByUsername(user.getUsername()));
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteUserByIdList(List<Integer> idList) {
        boolean flag = false;
        try{
            if(userMapper.deleteUserByIdList(idList) != 0 && userMapper.deleteUserDetailByIdList(idList) != 0) {
                flag = true;
            }
        } catch (Exception e){
            log.error("deleteUserByIdList method error. msg:" + e.getMessage());
            throw new RuntimeException();
        } finally {
            return flag;
        }
    }
}
