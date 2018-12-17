package com.numb.wfjz.common.enums;

import lombok.Getter;

/**
 * 响应状态码与提示信息的枚举类
 * @author Numblgw
 */
@Getter
public enum ResponseEnum {

    //账号不存在
    UNKNOWN_ACCOUNT(401, "账号不存在！"),

    //密码错误
    INCORRECT_CREDENTIALS(402, "密码错误！"),

    //没有访问权限
    UNAUTHORIZED(403, "没有访问权限！"),

    //登陆失败
    LOGIN_FAILURE(406, "登陆失败！"),

    //未知错误
    UNKNOWN_ERROR(500, "未知错误!"),

    //插入数据失败
    INSERT_ERROR(501,"插入数据失败！"),

    //删除数据失败
    DELETE_ERROR(502,"删除数据失败！"),

    //修改数据失败
    UPDATE_ERROR(503,"修改数据失败！"),

    //查询数据失败
    SELECT_ERROR(504,"查询数据失败！");

    private Integer code;

    private String msg;

    ResponseEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
