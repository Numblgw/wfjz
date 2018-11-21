package com.numb.wfjz.mapper;

import com.numb.wfjz.pojo.UserDetail;

import java.util.List;

public interface AdminMapper {
    /**
     * 查询用户详情信息列表
     * @return  UserDetail集合m
     */
    List<UserDetail> selectUserDetailList();

    /**
     * 向wfjz_user表中插入数据，并返回主键
     * @param userDetail    pojo类，只用到其父类中的属性
     * @return  插入的行数，1或0
     */
    int insertUser(UserDetail userDetail);

    /**
     * 向wfjz_user_detail表中插入数据，一般与 insertUser方法连用
     * @param userDetail    pojo
     * @return  插入的行数，1或0
     */
    int insertUserDetail(UserDetail userDetail);

    /**
     * 通过用户名查询用户
     * @param username  用户名
     * @return  用户id
     */
    int selectUserByUsername(String username);

    /**
     * 通过id查询用户详细信息
     * @param id    用户id
     * @return  用户详细信息pojo
     */
    UserDetail selectUserDetailById(int id);

    /**
     * 更新wfjz_user_detail表中的用户信息
     * @param userDetail    pojo
     * @return  更新的行数
     */
    int updateUserDetail(UserDetail userDetail);
}
