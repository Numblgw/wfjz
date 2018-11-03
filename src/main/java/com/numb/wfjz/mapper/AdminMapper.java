package com.numb.wfjz.mapper;

import com.numb.wfjz.pojo.User;

import java.util.List;

public interface AdminMapper {
    List<User> findUserList();
}
