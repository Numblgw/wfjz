--创建数据库
create databases if not exists wfjz;

--创建用户表
create table wfjz_user(
  `id` int(10)  primary key auto_increment comment '自增主键',
  `username` varchar(32) unique  comment '用户名，唯一',
  `password` varchar(32) not null comment '加密后密码',
  `gmt_create` datetime not null comment '创建该记录的时间',
  `gmt_modified` datetime comment '最后一次修改该数据的时间',
  key pk_id(id),
  key uk_username(username)
)
engine = InnoDB
default charset = utf8
comment = '用户基本表，仅包含登录验证相关的信息';

--创建角色表
create table wfjz_role(
  `id` int(10) primary key auto_increment comment '自增主键',
  `role_num` int(2) unique comment '角色代号',
  `description` varchar(32) not null comment '角色描述，即角色名称',
  `gmt_create` datetime not null comment '创建该记录的时间',
  `gmt_modified` datetime comment '最后一次修改该数据的时间'
)
engine = InnoDB
default charset = utf8
comment = '角色表，包括用户可以被授予的所有角色的信息';

--创建角色用户关联表
create table wfjz_user_role(
  `id` int(10) primary key auto_increment comment '自增主键',
  `user_id` int(10) not null comment 'wfjz_user表中的用户id，未设置外键',
  `role_id` int(10) not null comment 'wfjz_role表中的角色id，未设置外键',
  `gmt_create` datetime not null comment '创建该记录的时间',
  `gmt_modified` datetime comment '最后一次修改该记录的时间'
)
engine = InnoDB
default charset = utf8
comment = '用户角色关联表，记录用户具有的角色';

--创建用户详细信息表
create table wfjz_user_detail(
  `id` int(10) primary key auto_increment comment '自增主键',
  `user_id` int(10) unique comment 'wfjz_user表中的用户id，未设置外键',
  `nickname` varchar(32) default '还没有名字啊...' comment '昵称',
  `sex` char(1) not null default '1' comment '性别，0为女，1为男，默认男',
  `email` varchar(32) comment '邮箱',
  `phone` varchar(20) comment '电话',
  `address` varchar(100) comment '住址简介',
  `gmt_create` datetime not null comment '创建该记录的时间',
  `gmt_modified` datetime comment '最后一次修改该记录的时间',
  key pk_id(id),
  key uk_user_id(user_id)
)
engine = InnoDB
default charset = utf8
comment = '用户详细信息表，包括除用户名密码之外的信息';