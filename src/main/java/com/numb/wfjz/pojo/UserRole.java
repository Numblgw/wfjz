package com.numb.wfjz.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户角色关联pojo类
 * @author Numblgw
 * @date 2018/12/5 20:38
 */
@Data
public class UserRole implements Serializable {
    private static final long serialVersionUID = 3299975190871941151L;
    //自增主键
    private Integer id;
    //用户id
    private Integer userId;
    //角色id列表
    private List<Integer> roleIdList;
    //创建记录的时间
    private LocalDateTime gmtCreate;
    //最后一次修改记录的时间
    private LocalDateTime gmtModified;

    public UserRole(){}

    public UserRole(int userId, List<Integer> roleIdList, LocalDateTime gmtCreate){
        this.userId = userId;
        this.roleIdList = roleIdList;
        this.gmtCreate = gmtCreate;
    }

    public UserRole(int id, int userId, List<Integer> roleIdList, LocalDateTime gmtCreate, LocalDateTime gmtModified){
        this.id = id;
        this.userId = userId;
        this.roleIdList = roleIdList;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
