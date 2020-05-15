USE db_products;

create table sale_order_approval
(
    id                   int auto_increment
        primary key,
    approval_no          varchar(36)                             not null comment '申请单号',
    submit_user_id       varchar(36)                             not null comment '提交人ID',
    submit_user_name     varchar(36)   default ''                not null comment '提交人姓名',
    mobile               varchar(11)   default ''                not null comment '客户手机号',
    validate_type        tinyint       default 0                 not null comment '验证方式：1：验证码 2：无验证码',
    remark               varchar(255)  default ''                not null comment '备注信息',
    approver_id          varchar(36)   default ''                not null comment '审批人id',
    approver_name        varchar(36)   default ''                not null comment '审批人姓名',
    arrive_shop_time     datetime      default CURRENT_TIMESTAMP not null comment '到店时间',
    approval_status      tinyint       default 0                 not null comment '申请单状态0：待审批',
    approval_remark      varchar(200)  default ''                not null comment '审批备注',
    approval_time        datetime                                null comment '审批时间',
    follow_record_photos varchar(1500) default ''                not null comment '接待凭证，图片URL',
    create_time          datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time          datetime                                null on update CURRENT_TIMESTAMP comment '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审批列表';