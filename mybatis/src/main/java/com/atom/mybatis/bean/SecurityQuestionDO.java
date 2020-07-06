package com.atom.mybatis.bean;

import lombok.Data;

import java.util.Date;

/**
 * 安全问题
 *
 * @author Atom
 */
@Data
public class SecurityQuestionDO {
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
     * 修改人
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 数据版本
     */
    private Integer version;

    /**
     * 数据状态
     */
    private Integer status;

    /**
     * 账号ID
     */
    private Long accountId;


    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    /**
     * 问题编号
     */
    private Integer questionId;

}