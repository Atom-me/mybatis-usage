create table order_data(
    id bigint not null auto_increment comment '主键',
    order_id varchar(32) not null  default '' comment '订单号',
    seller_name varchar(32) not null default '' comment '销售姓名',
    seller_mobile varchar(255) not null default '' comment '销售电话',
    customer_identity_no varchar(255) not null default '' comment '身份证号',
    create_time datetime not null default now() comment '创建时间',
    update_time datetime not null on update current_timestamp comment '更新时间',
primary key id(id)

)engine innodb character set utf8mb4 comment '订单表';