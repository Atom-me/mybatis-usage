create table user_member_info(
    id bigint not null auto_increment,
    user_name varchar(32) not null default '' comment '客户姓名',
    real_name varchar(32) not null default '' comment '客户真实姓名',
    mobile_phone varchar(255) not null default '' comment '客户手机号',
    email varchar(255) not null default '' comment '客户邮箱',
    id_no varchar(255) not null default '' comment '客户身份证号',
    encode_version mediumint not null default 0 comment '加密版本号',
    create_time datetime not null default now() ,
    update_time datetime not null on update current_timestamp(),
    primary key id(id),
    unique key uniq_idx_id_no(id_no)
)engine innodb character set utf8mb4 comment '客户表';