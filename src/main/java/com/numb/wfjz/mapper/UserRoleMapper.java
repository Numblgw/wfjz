package com.numb.wfjz.mapper;

import com.numb.wfjz.pojo.UserRole;

import java.util.List;

/**
 * @ClassName UserRoleMapper
 * @Description 操作用户----权限表的mapper接口
 * @Author Numblgw
 * @Date 2018/12/8 13:56
 */

public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 通过用户id查询用户所具有的角色
     * @param id    用户id
     * @return  用户角色列表
     */
    UserRole selectRoleByUserId(int id);
}
