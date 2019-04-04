package com.numb.wfjz.common.enums;

import lombok.Getter;

/**
 * 角色枚举类
 * @author Numblgw
 * @date 2018/12/5 20:09
 */
@Getter
public enum RoleEnum {
    // 被封禁用户
    BANNED_USER(1, "bannedUser"),
    // 普通用户
    USER(2, "user"),
    // 普通管理员
    ADMIN(3, "admin"),
    // 超级管理员
    SUPER_ADMIN(4, "superAdmin");

    private int code;

    private String description;

    //通过该属性实现：通过枚举实例的描述值获取枚举实例
    private static final EnumFindHelper<RoleEnum, Integer> codeHelper = new EnumFindHelper<>(RoleEnum.class, (RoleEnum roleEnum)-> roleEnum.getCode());

    RoleEnum(int code, String description){
        this.code = code;
        this.description = description;
    }

    public static RoleEnum findByCode(int code, RoleEnum defaultValue){
        return codeHelper.find(code, defaultValue);
    }
}
