package com.numb.wfjz.mapper;

import com.numb.wfjz.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 查询是否存在用户名
     * @param username  用户名
     * @return  存在返回 1 不存在返回 0
     */
    int countByUsername(String username);
}
