package com.numb.wfjz.common.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EnumFindHelper
 * @Description 维护一个 枚举实例描述值----枚举实例  这样的集合，实现通过枚举实例描述值获得枚举实例
 * @Author Numblgw
 * @Date 2018/12/14 16:15
 */
@Slf4j
public class EnumFindHelper<T extends Enum<T>, K> {
    private Map<K, T> map = new HashMap<>();

    /**
     * 构造需传入该类的实例维护的具体枚举类和获得枚举实例描述值的方法（行为）
     * @param clazz 需要生成map对应列表的枚举类（class对象）
     * @param enumKeyGetter EnumKeyGetter函数式接口中的方法的具体实现。
     */
    public EnumFindHelper(Class<T> clazz, EnumKeyGetter<T, K> enumKeyGetter){
        try{
            EnumSet.allOf(clazz).stream().forEach((item)->{
                map.put(enumKeyGetter.getKey(item), item);
            });
        } catch (Exception e){
            log.error("EnumFindHelper error msg:" + e.getMessage());
        }
    }

    /**
     * 获取描述值对应的枚举实例
     * @param key 描述值
     * @param defaultValue  查找失败时的默认实例
     * @return
     */
    public T find(K key, T defaultValue){
        T value = map.get(key);
        if(null == value){
            value = defaultValue;
        }
        return value;
    }
}
