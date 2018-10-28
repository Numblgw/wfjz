package com.numb.wfjz.pojo;

import lombok.Data;

/**
 * 数据库中user表的po类
 * @author Numb
 * @date 2018年10月23日18:51:54
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String password;
}
