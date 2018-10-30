package com.numb.wfjz.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 *  用户与角色关系的pojo类
 * @author Numb
 */
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = -6257554844620243408L;

    private Integer id;

    private Integer userId;

    private Integer roleId;
}
