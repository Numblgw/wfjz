package com.numb.wfjz.mapper;

import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserDetail;
import com.numb.wfjz.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description 用户相关业务的数据层接口
 * @Author Numblgw
 * @Date 2018/11/1 13:52
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名获得user对象
     * @param username  用户名
     * @return  User对象或者null
     */
    User findByUsername(String username);

    /**
     * 查询是否存在用户名
     * @param username  用户名
     * @return  存在返回 1 不存在返回 0
     */
    int countByUsername(String username);

    /**
     * 查询用户详情信息列表，不包括已删除用户
     * @return  UserDetail集合m
     */
    List<UserDetail> selectUserDetailList();

    /**
     * 向wfjz_user表中插入数据，并返回主键
     * @param userDetail    pojo类，只用到其父类中的属性
     * @return  插入的行数，1或0
     */
    int insertUser(UserDetail userDetail);

    /**
     * 向wfjz_user_detail表中插入数据，一般与 insertUser方法连用
     * @param userDetail    pojo
     * @return  插入的行数，1或0
     */
    int insertUserDetail(UserDetail userDetail);

    /**
     * 通过用户名查询用户
     * @param username  用户名
     * @return  用户id
     */
    int selectIdByUsername(String username);

    /**
     * 通过id查询用户详细信息
     * @param id    用户id
     * @return  用户详细信息pojo
     */
    UserDetail selectUserDetailById(int id);

    /**
     * 更新wfjz_user_detail表中的用户信息
     * @param userDetail    pojo
     * @return  更新的行数
     */
    int updateUserDetail(UserDetail userDetail);

    /**
     * 通过id列表逻辑删除wfjz_user表中的数据
     * @param idList    id列表
     * @return  删除的行数
     */
    int deleteUserByIdList(List<Integer> idList);

    /**
     * 通过id列表逻辑删除wfjz_user_detail表中的数据
     * @param idList    id列表
     * @return  删除的行数
     */
    int deleteUserDetailByIdList(List<Integer> idList);
}
