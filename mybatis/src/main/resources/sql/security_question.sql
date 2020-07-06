
-- auto-generated definition
create table security_question
(
    id          bigint auto_increment comment '唯一主键'
        primary key,
    creator     varchar(30)      not null comment '创建人',
    create_time datetime         not null comment '创建时间',
    modifier    varchar(30)      not null comment '修改人',
    update_time datetime         not null comment '修改时间',
    version     int(6) default 0 not null comment '数据版本',
    status      int(6) default 1 not null comment '数据状态',
    account_id  bigint           null comment '账号ID',
    question    varchar(256)     not null comment '问题',
    answer      varchar(256)     not null comment '答案',
    question_id int(2)           not null comment '问题编号'
)
    comment '安全问题' collate = utf8mb4_unicode_ci;


insert into db_products.security_question (id, creator, create_time, modifier, update_time, version, status, account_id, question, answer, question_id) values (1, 'atom', '2020-07-06 22:23:43', 'atom', '2020-07-06 22:23:49', 1, 1, 123, 234, 'fsdwer', 12);
insert into db_products.security_question (id, creator, create_time, modifier, update_time, version, status, account_id, question, answer, question_id) values (61, 'atom', '2020-07-06 22:23:43', 'atom', '2020-07-06 22:23:49', 1, 1, 123, 234, 'fsdwer', 12);
insert into db_products.security_question (id, creator, create_time, modifier, update_time, version, status, account_id, question, answer, question_id) values (62, 'atom', '2020-07-06 22:23:43', 'atom', '2020-07-06 22:23:49', 1, 1, 123, 234, 'fsdwer', 1);
