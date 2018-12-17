package com.numb.wfjz.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 角色pojo
 * @author Numblgw
 * @date 2018/12/5 20:35
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -677083667284736274L;
    //自增id
    private Integer id;
    //角色名称
    private String roleName;
    //角色描述
    private String description;
    //创建该条记录的时间
    private LocalDateTime gmtCreate;
    //最后一次修改该记录的时间
    private LocalDateTime gmtModified;
}
