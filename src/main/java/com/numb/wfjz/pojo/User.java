package com.numb.wfjz.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据库中user表的pojo类
 * @author Numb
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 2675625947798064817L;
    //id
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
}
