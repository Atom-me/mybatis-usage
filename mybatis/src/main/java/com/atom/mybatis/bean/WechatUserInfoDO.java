package com.atom.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 微信用户信息表
 *
 * @author Atom
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WechatUserInfoDO {

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 数据状态
     */
    private Integer status;
    /**
     * 数据版本号
     */
    private Integer version;

    /**
     * 微信公众平台标识
     */
    private String openid;
    /**
     * 微信开发平台标识
     */
    private String unionid;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 微信头像地址
     */
    private String headImgUrl;
    /**
     * 公众号APPID
     */
    private String appid;
    /**
     * 关注标识
     */
    private Integer followFlag;

}
