package com.numb.wfjz.mapper;

import com.numb.wfjz.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 通过id查询用户
     * @param id    用户唯一标识
     * @return      用户pojo
     */
    User findById(int id);

    /**
     * 插入一条用户信息
     * @param user  用户pojo
     */
    int insertOne(User user);

    /**
     * 查询是否存在用户名
     * @param username  用户名
     * @return  存在返回 1 不存在返回 0
     */
    int countByUsername(String username);
}
