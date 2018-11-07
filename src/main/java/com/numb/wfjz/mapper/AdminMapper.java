package com.numb.wfjz.mapper;

import com.numb.wfjz.pojo.UserDetail;

import java.util.List;

public interface AdminMapper {
    List<UserDetail> selectUserDetailList();
}
