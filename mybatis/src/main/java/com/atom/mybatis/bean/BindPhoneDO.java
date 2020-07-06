package com.atom.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 手机号绑定表
 *
 * @author Atom
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindPhoneDO {
    /**
     * 唯一主键
     */
    private Long id;
    /**
     * 创建人
     */
    private String creator;
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
     * 手机号码
     */
    private String mobilePhone;
    /**
     * 绑定时间
     */
    private Date bindDate;

}
