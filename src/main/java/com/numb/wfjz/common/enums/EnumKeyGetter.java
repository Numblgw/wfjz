package com.numb.wfjz.common.enums;

/**
 * @ClassName EnumKeyGetter
 * @Description 通过枚举实例包含的描述值，获得枚举实例的函数式接口
 * @Author Numblgw
 * @Date 2018/12/14 16:30
 */
public interface EnumKeyGetter<T extends Enum<T>, K> {
    /**
     * 获取枚举实例中的描述值，用来初始化反向查找帮助类中的反向映射。
     * @param enumValue 枚举实例
     * @return  描述值
     */
    K getKey(T enumValue);
}
