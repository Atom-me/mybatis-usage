create database emoji_utf8 char set utf8;
CREATE TABLE emoji_utf8.`ua_wechat_userinfo` (

                                      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                      `create_time` datetime NOT NULL COMMENT '创建时间',
                                      `update_time` datetime NOT NULL COMMENT '修改时间',
                                      `version` int(6) NOT NULL DEFAULT '0' COMMENT '数据版本',
                                      `status` int(6) NOT NULL DEFAULT '1' COMMENT '数据状态',
                                      `openid` varchar(128)  DEFAULT '' COMMENT '微信公众平台标识',
                                      `unionid` varchar(128)  DEFAULT '' COMMENT '微信开放平台标识',
                                      `nickname` varchar(128)  DEFAULT '' COMMENT '微信昵称',
                                      `head_img_url` varchar(2000)  DEFAULT '' COMMENT '微信头像地址',
                                      `appid` varchar(100)  DEFAULT '' COMMENT '公众号APPID',
                                      `follow_flag` tinyint(1) DEFAULT '1' COMMENT '关注标识',
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `idx_unionid` (`unionid`) USING BTREE,
                                      KEY `idx_openid` (`openid`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='微信用户信息';