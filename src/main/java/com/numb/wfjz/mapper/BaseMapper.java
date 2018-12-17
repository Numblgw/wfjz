package com.numb.wfjz.mapper;

import java.util.List;

/**
 * 基础的mapper接口，其他mapper需要实现这个mapper
 * @param <T>   类型参数
 * @author Numblgw
 * @date 2018/12/6 12:51
 */
public interface BaseMapper<T> {

    /**
     * 通过pojo类插入一行
     * @param entity    实体类
     * @return  插入的行数
     */
    int insert(T entity);

    /**
     * 通过id逻辑删除一行
     * @param id    id
     * @return  删除的行数
     */
    int deleteById(int id);

    /**
     * 逻辑删除列表中所有元素
     * @param list  元素列表或者id列表等
     * @return  受作用的行数，不为0则删除成功
     */
    int deleteByList(List list);

    /**
     * 通过pojo类更新一行
     * @param entity    实体类
     * @return  修改的行数
     */
    int update(T entity);

    /**
     * 通过id查询
     * @param id    id
     * @return  pojo
     */
    T selectById(int id);

    /**
     * 查询列表
     * @return  列表
     */
    List<T> selectList();
}
