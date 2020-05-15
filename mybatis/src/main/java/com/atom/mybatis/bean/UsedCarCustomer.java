package com.atom.mybatis.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UsedCarCustomer {
    /**
     * 主键
     */
    private Long id;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 客户状态
     */
    private String customerStatus;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 客户类型(1,个人；2，公司)
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用电话
     */
    private String alternatePhone;
    /**
     * 客户住址
     */
    private String address;
    /**
     * 微信标签
     */
    private String tags;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}