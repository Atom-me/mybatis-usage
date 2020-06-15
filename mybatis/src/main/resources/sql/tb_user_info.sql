DROP TABLE IF EXISTS tb_user_info;

CREATE TABLE tb_user_info
(
    id         BIGINT(20) NOT NULL auto_increment COMMENT '主键ID',
    name       VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age        INT(11) NULL DEFAULT NULL COMMENT '年龄',
    gender     varchar(32) NULL DEFAULT NULL COMMENT '性别,0:MALE, 1:FEMALE',
    grade      int (2) NULL DEFAULT NULL COMMENT '年级',
    email      VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    user_state INT(1) NULL DEFAULT NULL COMMENT '用户状态',
    str_enum   VARCHAR(50) NULL,
    PRIMARY KEY (id)
);

DELETE FROM tb_user_info;

INSERT INTO tb_user_info (id, name, age, email, grade, gender, user_state, str_enum)
VALUES (2, 'Jack', 3, 'test2@baomidou.com', 1, 0, 1, 'one'),
       (3, 'Tom', 1, 'test3@baomidou.com', 2, 1, 1, 'one'),
       (1, 'Billie', 2, 'test5@baomidou.com', 3, null, 1, 'two');