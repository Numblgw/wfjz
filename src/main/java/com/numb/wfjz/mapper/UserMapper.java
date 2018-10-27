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
}
