package com.numb.wfjz.common.enums;

import lombok.Getter;

/**
 * 响应状态码与提示信息的枚举类
 * @author Numblgw
 */
@Getter
public enum ResponseEnum {

    //插入数据失败
    INSERT_ERROR(501,"插入数据失败！"),
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
