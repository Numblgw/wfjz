package com.numb.wfjz.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据库role表的pojo类
 * @author Numb
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1208328249920351008L;
    //表中自增id
    private Integer id;

    //角色编号
    private Integer role_num;

    //角色描述，即角色标号对应的角色
    private String description;
}
