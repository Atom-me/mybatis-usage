create table used_car_customer(
    id bigint not null auto_increment comment '主键',
    name varchar(32) not null default '' comment '姓名',
    telephone varchar(11) not null default '' comment '手机号',
    age mediumint not null default 0 comment '年龄',
    customer_status varchar(32) not null default '' comment '客户状态',
    gender tinyint not null default 0 comment '性别',
    type tinyint not null default 0 comment '客户类型',
    remark varchar(255)  not null default '' comment '备注',
    alternate_phone varchar(11) not null default '' comment '客户备用电话',
    address varchar(255) not null default '' comment '客户地址',
    tags varchar(255) not null default '' comment '客户微信标签',
    created_time datetime not null default now() comment '创建时间',
    update_time datetime not null on update current_timestamp comment '更新时间',
    primary key id(id)

)engine innodb character set utf8mb4 comment '客户表';