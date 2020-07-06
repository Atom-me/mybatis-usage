create table bind_phone
(
    id           bigint auto_increment comment '主键ID',
    creator      varchar(32) charset utf8            not null comment '创建者',
    create_time  datetime                            not null comment '创建时间',
    update_time  datetime                            not null comment '修改时间',
    version      int(6)                   default 0  not null comment '数据版本',
    status       int(6)                   default 1  not null comment '数据状态',
    mobile_phone varchar(50) charset utf8 default '' not null comment '手机号码',
    bind_date    datetime                            not null comment '绑定时间',
primary key id(id)
)
    comment '手机绑定信息' collate = utf8mb4_unicode_ci;