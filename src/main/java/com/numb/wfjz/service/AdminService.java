package com.numb.wfjz.service;

import com.github.pagehelper.PageInfo;

public interface AdminService {
    /**
     * 获取用户列表,并且使用分页插件分页
     * @return  PageInfo对象
     */
    PageInfo getUserListByPage(int pageNum , int pageSize);
}
