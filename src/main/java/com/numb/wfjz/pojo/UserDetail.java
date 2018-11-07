package com.numb.wfjz.pojo;

import lombok.Data;

@Data
public class UserDetail extends User {
    //昵称
    private String nickname;
    //性别，1男，0女
    private String sex;
    //邮箱
    private String email;
    //电话
    private String phone;
    //地址
    private String address;
}
